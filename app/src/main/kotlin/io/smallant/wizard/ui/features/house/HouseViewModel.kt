package io.smallant.wizard.ui.features.house

import android.app.Application
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.R
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class HouseViewModel(application: Application) : BaseViewModel(application) {

    private val _wizards: MutableLiveData<List<Wizard>> = MutableLiveData()
    private val _spinner = MutableLiveData<Boolean>()
    private val _empty = MutableLiveData<String>()

    private val resources: Resources by lazy { getApplication<Application>().resources }

    val wizards: LiveData<List<Wizard>>
        get() = _wizards

    val spinner: LiveData<Boolean>
        get() = _spinner

    val empty: LiveData<String>
        get() = _empty

    fun fetchData(houseId: Int) {
        _empty.value = null
        launchDataLoad {
            val wizards = wizardRepository.fetchWizardsFromHouse(houseId)
            if (wizards.isEmpty())
                _empty.value = resources.getString(R.string.house_empty)
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