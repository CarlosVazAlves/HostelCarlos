package carlos.alves.hostelcarlos.di.modules

import carlos.alves.hostelcarlos.ui.details.DetailsActivity
import carlos.alves.hostelcarlos.ui.main.MainActivity
import carlos.alves.hostelcarlos.ui.results.ResultsActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributesMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun contributesResultsActivity(): ResultsActivity

    @ContributesAndroidInjector
    abstract fun contributesDetailsActivity(): DetailsActivity
}