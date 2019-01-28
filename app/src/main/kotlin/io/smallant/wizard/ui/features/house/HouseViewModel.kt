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
    private val _error = MutableLiveData<String>()
    private val _displayContent = MutableLiveData<Boolean>()

    private val resources: Resources by lazy { getApplication<Application>().resources }

    val wizards: LiveData<List<Wizard>>
        get() = _wizards

    val spinner: LiveData<Boolean>
        get() {
            _displayContent.value = (_spinner.value == false && _empty.value == null && _error.value == null)
            return _spinner
        }

    val empty: LiveData<String>
        get() {
            _displayContent.value = _empty.value == null
            return _empty
        }

    val error: LiveData<String>
        get() {
            _displayContent.value = _error.value == null
            return _error
        }

    val displayContent: LiveData<Boolean>
        get() = _displayContent

    fun fetchData(houseId: Int) {
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
                    _empty.value = null
                    _error.value = null
                    _spinner.value = true
                    block()
                } catch (error: IOException) {
                    _error.value = error.message
                    //sendSnackbar(error.message ?: "An error occured")
                } finally {
                    _spinner.value = false
                }
            }
        }
    }
}