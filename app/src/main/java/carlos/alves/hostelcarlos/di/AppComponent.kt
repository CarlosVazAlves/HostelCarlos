package carlos.alves.hostelcarlos.di

import android.app.Application
import carlos.alves.hostelcarlos.HostelApp
import carlos.alves.hostelcarlos.di.modules.ActivityBuilderModule
import carlos.alves.hostelcarlos.di.modules.NetworkModule
import carlos.alves.hostelcarlos.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityBuilderModule::class,
        ViewModelModule::class,
        NetworkModule::class,
    ]
)
interface AppComponent : AndroidInjector<HostelApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    override fun inject(app: HostelApp)
}