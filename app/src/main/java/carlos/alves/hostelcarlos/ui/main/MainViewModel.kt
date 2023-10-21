package carlos.alves.hostelcarlos.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import carlos.alves.hostelcarlos.api.ApiCalls
import javax.inject.Inject

class MainViewModel @Inject constructor(private val apiCalls: ApiCalls) : ViewModel() {

    private val isLoading: MutableLiveData<Boolean> = MutableLiveData(false)

    fun getSomeHostels() = apiCalls.fetchHostelsInfo()

    fun getLoadingStatus() = isLoading

    fun changeLoadingStatus(isLoading: Boolean) {
        this.isLoading.value = isLoading
    }
}