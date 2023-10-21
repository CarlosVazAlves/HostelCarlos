package carlos.alves.hostelcarlos.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import carlos.alves.hostelcarlos.databinding.ActivityMainBinding
import carlos.alves.hostelcarlos.utils.Converters.Companion.convertToHostelDetailsArrayList
import carlos.alves.hostelcarlos.utils.NavigationManager
import dagger.android.AndroidInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val compositeDisposable = CompositeDisposable()
    private lateinit var viewModel: MainViewModel

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory


    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]

        viewModel.getLoadingStatus().observe(this) {
            binding.loading.visibility = if (it) View.VISIBLE else View.INVISIBLE
        }

        binding.fetchButton.setOnClickListener {
            viewModel.changeLoadingStatus(true)
            val disposable = viewModel.getSomeHostels().subscribeOn(Schedulers.newThread()).subscribe(
                { hostels ->
                    navigationManager.goToResults(this, convertToHostelDetailsArrayList(this, hostels))
                    runOnUiThread { viewModel.changeLoadingStatus(false) }},
                { error ->
                    runOnUiThread { Toast.makeText(this, error.message, Toast.LENGTH_LONG).show(); viewModel.changeLoadingStatus(false) }
                }
            )
            compositeDisposable.add(disposable)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.dispose()
    }
}