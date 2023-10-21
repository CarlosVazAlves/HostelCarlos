package carlos.alves.hostelcarlos.utils

import android.content.Context
import android.content.Intent
import androidx.core.content.ContextCompat
import carlos.alves.hostelcarlos.api.HostelDetails
import carlos.alves.hostelcarlos.ui.details.DetailsActivity
import carlos.alves.hostelcarlos.ui.results.ResultsActivity
import javax.inject.Inject

class NavigationManager @Inject constructor() {

    fun goToResults(context: Context, hostelsDetailsList: ArrayList<HostelDetails>) {
        val intent = Intent(context, ResultsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(AppConstants.HOSTELS_LIST, hostelsDetailsList)
        ContextCompat.startActivity(context, intent, null)
    }

    fun goToDetails(context: Context, hostelDetails: HostelDetails) {
        val intent = Intent(context, DetailsActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        intent.putExtra(AppConstants.HOSTEL_ITEM, hostelDetails)
        ContextCompat.startActivity(context, intent, null)
    }
}