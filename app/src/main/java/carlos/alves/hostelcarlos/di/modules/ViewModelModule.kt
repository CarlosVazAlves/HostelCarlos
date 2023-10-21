package carlos.alves.hostelcarlos.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import carlos.alves.hostelcarlos.di.ViewModelFactory
import carlos.alves.hostelcarlos.di.ViewModelKey
import carlos.alves.hostelcarlos.ui.details.DetailsViewModel
import carlos.alves.hostelcarlos.ui.main.MainViewModel
import carlos.alves.hostelcarlos.ui.results.ResultsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindsViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindsMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ResultsViewModel::class)
    abstract fun bindsResultsViewModel(resultsViewModel: ResultsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DetailsViewModel::class)
    abstract fun bindsDetailsViewModel(detailsViewModel: DetailsViewModel): ViewModel
}