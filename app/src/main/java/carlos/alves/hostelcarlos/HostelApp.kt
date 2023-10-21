package carlos.alves.hostelcarlos

import android.app.Application
import android.content.Context
import carlos.alves.hostelcarlos.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class HostelApp @Inject constructor() : Application(), HasAndroidInjector {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Any>

    override fun androidInjector() : AndroidInjector<Any> = androidInjector

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        DaggerAppComponent.builder().application(this).build().inject(this)
    }
}