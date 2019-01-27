package io.smallant.wizard.ui.features.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.houses.HowgwartHouse
import io.smallant.wizard.data.models.houses.Hufflepuff
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.ui.base.BaseViewModel
import io.smallant.wizard.utils.Event
import kotlinx.coroutines.*
import java.io.IOException

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

    private val _spinner = MutableLiveData<Boolean>()
    private val _houses: MutableLiveData<List<HowgwartHouse>> = MutableLiveData()
    private val _openHouse: MutableLiveData<Event<Pair<Int, String>>> = MutableLiveData()

    val houses: LiveData<List<HowgwartHouse>>
        get() = _houses

    val spinner: LiveData<Boolean>
        get() = _spinner

    val openHouse: LiveData<Event<Pair<Int, String>>>
        get() = _openHouse

    fun houseClicked() {
        val house = Hufflepuff()
        _openHouse.value = Event(Pair(house.id, house.name))
    }

    fun loadHouses() {
        uiScope.launch {
            try {
                _spinner.value = true
                _houses.value = wizardRepository.fetchHouses()
            } catch (exception: IOException) {
                _houses.value = null
            } finally {
                _spinner.value = false
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}