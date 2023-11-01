package carlos.alves.hostelcarlos.ui.main

import android.content.Context
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import carlos.alves.hostelcarlos.api.ApiCalls
import carlos.alves.hostelcarlos.utils.Converters
import carlos.alves.hostelcarlos.utils.NavigationManager
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiCalls: ApiCalls) : ViewModel() {

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    private val compositeDisposable = CompositeDisposable()
    @Inject
    lateinit var navigationManager: NavigationManager

    fun getSomeHostels(context: Context) {
        changeLoadingStatus(true)
        val disposable = apiCalls.fetchHostelsInfo().subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(
            { hostels ->
                navigationManager.goToResults(context, Converters.convertToHostelDetailsArrayList(context, hostels))
                changeLoadingStatus(false)
            },
            { error ->
                Toast.makeText(context, error.message, Toast.LENGTH_LONG).show()
                changeLoadingStatus(false)
            }
        )
        compositeDisposable.add(disposable)
    }

    fun getLoadingStatus() = isLoading

    private fun changeLoadingStatus(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}