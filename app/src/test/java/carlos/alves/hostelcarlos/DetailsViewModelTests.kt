package carlos.alves.hostelcarlos

import carlos.alves.hostelcarlos.ui.details.DetailsViewModel
import org.junit.Assert
import org.junit.Before
import org.junit.Test

class DetailsViewModelTests {

    companion object {
        const val LINK1 = "Link1"
        const val LINK2 = "Link2"
        const val LINK3 = "Link3"
    }

    private val listImageLinks = listOf(LINK1, LINK2, LINK3)
    private lateinit var detailsViewModel: DetailsViewModel

    @Before
    fun setup() {
        detailsViewModel = DetailsViewModel()
        detailsViewModel.loadImageLinks(listImageLinks)
    }

    @Test
    fun `Get current image link at start`() {
        val link = detailsViewModel.loadCurrentImageLink()
        Assert.assertEquals(LINK1, link)
    }

    @Test
    fun `Get next image link`() {
        val link = detailsViewModel.loadNextImageLink()
        Assert.assertEquals(LINK2, link)
    }

    @Test
    fun `Get previous image link`() {
        val link = detailsViewModel.loadPreviousImageLink()
        Assert.assertEquals(LINK3, link)
    }
}