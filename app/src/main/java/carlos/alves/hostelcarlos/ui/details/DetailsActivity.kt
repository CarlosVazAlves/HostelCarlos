package carlos.alves.hostelcarlos.ui.details

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.AnimationUtils
import androidx.core.content.IntentCompat
import androidx.lifecycle.ViewModelProvider
import carlos.alves.hostelcarlos.R
import carlos.alves.hostelcarlos.api.HostelDetails
import carlos.alves.hostelcarlos.databinding.ActivityDetailsBinding
import carlos.alves.hostelcarlos.utils.AppConstants
import coil.load
import dagger.android.AndroidInjection
import javax.inject.Inject

class DetailsActivity : AppCompatActivity() {

    private val binding: ActivityDetailsBinding by lazy { ActivityDetailsBinding.inflate(layoutInflater) }
    private lateinit var viewModel: DetailsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val hostelByIntent = IntentCompat.getParcelableExtra(intent, AppConstants.HOSTEL_ITEM, HostelDetails::class.java)!!
        viewModel = ViewModelProvider(this, viewModelFactory)[DetailsViewModel::class.java]
        viewModel.loadImageLinks(hostelByIntent.imageLinks)
        val priceWithSign = "${hostelByIntent.lowestPricePerNight}€"

        binding.hostelThumbnail.load(viewModel.loadCurrentImageLink())
        binding.hostelName.text = hostelByIntent.name
        binding.hostelLocation.text = hostelByIntent.district
        binding.hostelLowestPrice.text = priceWithSign
        binding.hostelRating.text = hostelByIntent.overallRating
        binding.hostelOverview.text = hostelByIntent.overview

        binding.backButton.setOnClickListener { finish() }

        val entryPreviousAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_from_start)
        binding.previousButton.setOnClickListener {
            binding.hostelThumbnail.load(viewModel.loadPreviousImageLink())
            binding.hostelThumbnail.startAnimation(entryPreviousAnimation)
        }

        val entryNextAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_from_end)
        binding.nextButton.setOnClickListener {
            binding.hostelThumbnail.load(viewModel.loadNextImageLink())
            binding.hostelThumbnail.startAnimation(entryNextAnimation)
        }
    }
}