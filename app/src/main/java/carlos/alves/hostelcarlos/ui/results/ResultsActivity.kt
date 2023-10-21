package carlos.alves.hostelcarlos.ui.results

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.IntentCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import carlos.alves.hostelcarlos.R
import carlos.alves.hostelcarlos.api.HostelDetails
import carlos.alves.hostelcarlos.databinding.ActivityResultsBinding
import carlos.alves.hostelcarlos.utils.AppConstants
import carlos.alves.hostelcarlos.utils.NavigationManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class ResultsActivity : AppCompatActivity() {

    private val binding: ActivityResultsBinding by lazy { ActivityResultsBinding.inflate(layoutInflater) }
    private val recyclerView by lazy { findViewById<RecyclerView>(R.id.hostels_results) }
    private lateinit var recyclerAdapter: HostelResultsAdapter

    @Inject
    lateinit var navigationManager: NavigationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val hostelsListByIntent = IntentCompat.getParcelableArrayListExtra(intent, AppConstants.HOSTELS_LIST, HostelDetails::class.java)

        recyclerAdapter = HostelResultsAdapter(this)
        recyclerView.adapter = recyclerAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        recyclerAdapter.setHostels(hostelsListByIntent!!)

        recyclerAdapter.getCurrentHostel().observe(this) {
            navigationManager.goToDetails(this, it)
        }
    }
}