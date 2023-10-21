package carlos.alves.hostelcarlos.ui.details

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : ViewModel() {

    private lateinit var imageLinks: List<String>
    private var currentImageIndex = 0

    fun loadImageLinks(imageLinks: List<String>) {
        this.imageLinks = imageLinks
    }

    fun loadCurrentImageLink() = imageLinks[currentImageIndex]

    fun loadNextImageLink(): String {
        if (currentImageIndex == (imageLinks.size - 1)) {
            currentImageIndex = 0
        } else {
            currentImageIndex += 1
        }
        return loadCurrentImageLink()
    }

    fun loadPreviousImageLink(): String {
        if (currentImageIndex == 0) {
            currentImageIndex = (imageLinks.size - 1)
        } else {
            currentImageIndex -= 1
        }
        return loadCurrentImageLink()
    }
}