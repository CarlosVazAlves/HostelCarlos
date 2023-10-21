package carlos.alves.hostelcarlos.di.modules

import carlos.alves.hostelcarlos.api.ApiService
import carlos.alves.hostelcarlos.utils.AppConstants
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    internal fun provideRetrofitClient(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://gist.githubusercontent.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    internal fun okHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(AppConstants.TIMEOUT, TimeUnit.SECONDS)
            .build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)
}