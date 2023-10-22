package carlos.alves.hostelcarlos

import carlos.alves.hostelcarlos.api.District
import carlos.alves.hostelcarlos.api.HostelInfo
import carlos.alves.hostelcarlos.api.ImagesGallery
import carlos.alves.hostelcarlos.api.LowestPricePerNight
import carlos.alves.hostelcarlos.api.OverallRating

class HostelMock {
    companion object {
        private const val NAME = "Cool Hostel"
        private const val LOCATION = "Oeiras"
        private const val LOWEST_PRICE = 0.1F
        private const val OVERVIEW = "Very Cool"
        private const val OVERALL_RATING = 10
        private const val IMAGE_LINK_PREFIX = "image/link/prefix"
        private const val IMAGE_LINK_SUFFIX = "image/link/suffix"
        private const val IS_FEATURED = true

        val hostelRight = HostelInfo(
            NAME,
            District(LOCATION),
            LowestPricePerNight(LOWEST_PRICE),
            OVERVIEW, OverallRating(OVERALL_RATING),
            listOf(ImagesGallery(IMAGE_LINK_PREFIX, IMAGE_LINK_SUFFIX)),
            IS_FEATURED)

        val hostelWrong = HostelInfo(
            NAME,
            District(LOCATION),
            LowestPricePerNight(LOWEST_PRICE),
            OVERVIEW, OverallRating(OVERALL_RATING),
            listOf(ImagesGallery(IMAGE_LINK_PREFIX, IMAGE_LINK_SUFFIX)),
            false)
    }
}