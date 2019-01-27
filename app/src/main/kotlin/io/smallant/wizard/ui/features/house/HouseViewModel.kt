package io.smallant.wizard.ui.features.house

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class HouseViewModel(application: Application) : BaseViewModel(application) {

    private val _spinner = MutableLiveData<Boolean>()
    private val _wizards: MutableLiveData<List<Wizard>> = MutableLiveData()

    val wizards: LiveData<List<Wizard>>
        get() = _wizards

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun fetchData(houseId: Int) {
        launchDataLoad {
            val wizards = wizardRepository.fetchWizardsFromHouse(houseId)
            _wizards.value = wizards
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
}