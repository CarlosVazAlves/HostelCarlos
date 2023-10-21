package carlos.alves.hostelcarlos.api

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


data class HostelsList(val properties: List<HostelInfo>)

data class HostelInfo(val name: String, val district: District?, val lowestPricePerNight: LowestPricePerNight, val overview: String, val overallRating: OverallRating?, val imagesGallery: List<ImagesGallery>, val isFeatured: Boolean)

data class District(val name: String)

data class LowestPricePerNight(val value: Float)

data class OverallRating(val overall: Int)

data class ImagesGallery(val prefix: String, val suffix: String)

@Parcelize
data class HostelDetails(val name: String, val district: String, val lowestPricePerNight: String, val overview: String, val overallRating: String, val imageLinks: List<String>, val isFeatured: Boolean) : Parcelable