package carlos.alves.hostelcarlos.api

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface ApiService {

    @GET("ollerandreshw/e02c83a2c973c625bbc250e1d93a2040/raw/4fcbd3d63e37d69fa3524176e37c35839e4575c2/properties.json")
    @Headers("Accept: application/json")
    fun getHostelsRx(): Single<HostelsList>
}