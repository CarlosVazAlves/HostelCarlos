package carlos.alves.hostelcarlos

import carlos.alves.hostelcarlos.api.ApiService
import carlos.alves.hostelcarlos.api.HostelsList
import io.reactivex.Single

class FakeApiService : ApiService {
    override fun getHostelsRx(): Single<HostelsList> {
        return Single.just(HostelsList(listOf(HostelMock.hostelRight)))
    }
}