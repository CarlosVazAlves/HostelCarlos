package carlos.alves.hostelcarlos.api

import io.reactivex.Single
import javax.inject.Inject

class ApiCalls @Inject constructor(private val apiService: ApiService) {

    fun fetchHostelsInfo(): Single<HostelsList> {
        return apiService.getHostelsRx()
    }
}