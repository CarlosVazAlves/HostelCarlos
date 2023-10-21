package carlos.alves.hostelcarlos.utils

import android.content.Context
import carlos.alves.hostelcarlos.R
import carlos.alves.hostelcarlos.api.HostelDetails
import carlos.alves.hostelcarlos.api.HostelsList

class Converters {

    companion object {
        fun convertDollarToEuroString(dollars: Float) = String.format("%.2f", dollars * AppConstants.DOLLAR_TO_EURO_CONVERSION_FACTOR)

        fun convertRatingToDecimalString(context: Context, rating: Int?) = if (rating == null || rating == 0) context.getString(R.string.not_rated) else (rating * 0.1).toString()

        fun convertToHostelDetailsArrayList(context: Context, hostelsList: HostelsList): ArrayList<HostelDetails> {
            val hostelDetailsList = arrayListOf<HostelDetails>()
            hostelsList.properties.forEach { hostelInfo -> hostelDetailsList.add(
                HostelDetails(
                    hostelInfo.name,
                    hostelInfo.district?.name ?: context.getString(R.string.no_info),
                    convertDollarToEuroString(hostelInfo.lowestPricePerNight.value),
                    hostelInfo.overview,
                    convertRatingToDecimalString(context, hostelInfo.overallRating?.overall),
                    hostelInfo.imagesGallery.map { imagesGallery ->  "https://${imagesGallery.prefix}${imagesGallery.suffix}" },
                    hostelInfo.isFeatured
                )
            ) }
            return hostelDetailsList
        }
    }
}