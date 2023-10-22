package carlos.alves.hostelcarlos

import carlos.alves.hostelcarlos.api.ApiCalls
import carlos.alves.hostelcarlos.api.HostelsList
import carlos.alves.hostelcarlos.ui.main.MainViewModel
import org.junit.Before
import org.junit.Test

class MainViewModelTests {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var apiCalls: ApiCalls
    private val fakeApiService = FakeApiService()

    @Before
    fun setup() {
        apiCalls = ApiCalls(fakeApiService)
        mainViewModel = MainViewModel(apiCalls)
    }

    @Test
    fun `Api call returns the right Hostel`() {
        val hostels = mainViewModel.getSomeHostels().test()
        hostels.assertValue(HostelsList(listOf(HostelMock.hostelRight)))
        hostels.assertNever(HostelsList(listOf(HostelMock.hostelWrong)))
    }
}