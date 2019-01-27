package io.smallant.wizard.ui.features.house

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.houses.Hufflepuff
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.ui.base.BaseViewModel
import io.smallant.wizard.utils.Event
import kotlinx.coroutines.*
import java.io.IOException

class HouseViewModel(application: Application): BaseViewModel(application) {
    private val viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

    private val _spinner = MutableLiveData<Boolean>()
    private val _content: MutableLiveData<String> = MutableLiveData()
    private val _openHouse: MutableLiveData<Event<Pair<Int, String>>> = MutableLiveData()

    val content: LiveData<String>
        get() = _content

    val spinner: LiveData<Boolean>
        get() = _spinner

    val openHouse: LiveData<Event<Pair<Int, String>>>
        get() = _openHouse

    fun houseClicked() {
        val house = Hufflepuff()
        _openHouse.value = Event(Pair(house.id, house.name))
    }

    fun fetchData() {
        launchDataLoad {
            val randomId = (1..6).random()
            val firstWizard = wizardRepository.fetchWizard(randomId)
            _content.value = firstWizard.fullname
        }
    }

    private fun launchDataLoad(block: suspend () -> Unit): Job {
        return uiScope.launch {
            if (_spinner.value == null || _spinner.value == false) {
                try {
                    _spinner.value = true
                    block()
                } catch (error: IOException) {
                    sendSnackbar(error.message ?: "An error occured")
                } finally {
                    _spinner.value = false
                }
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }
}