package carlos.alves.hostelcarlos.api

import android.content.Context
import android.os.Parcelable
import carlos.alves.hostelcarlos.R
import kotlinx.parcelize.Parcelize

/**
 * HostelDTO to be used with received json from API
 */
data class HostelsList(val properties: List<HostelInfo>)

data class HostelInfo(val name: String, val district: District?, val lowestPricePerNight: LowestPricePerNight, val overview: String, val overallRating: OverallRating?, val imagesGallery: List<ImagesGallery>, val isFeatured: Boolean)

data class District(val name: String)

data class LowestPricePerNight(val value: Float)

data class OverallRating(val overall: Int)

data class ImagesGallery(val prefix: String, val suffix: String)

/**
 * Hostel Parcel Object to passed around by activities
 */
@Parcelize
class HostelDetails(val name: String, val district: String, val lowestPricePerNight: String, val overview: String, val overallRating: String, val imageLinks: List<String>, val isFeatured: Boolean) : Parcelable {
    fun formattedName(context: Context) : String {
        return String.format(context.getString(R.string.name), this.name)
    }

    fun formattedLocation(context: Context) : String {
        return String.format(context.getString(R.string.location), this.district)
    }

    fun formattedLowestPrice(context: Context) : String {
        return String.format(context.getString(R.string.price), this.lowestPricePerNight)
    }

    fun formattedRating(context: Context) : String {
        return String.format(context.getString(R.string.rating), this.overallRating)
    }
}