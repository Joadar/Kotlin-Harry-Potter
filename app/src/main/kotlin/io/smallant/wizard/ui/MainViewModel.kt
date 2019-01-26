package io.smallant.wizard.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.IOException

class MainViewModel(application: Application) : BaseViewModel(application) {

    private val viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

    private val _spinner = MutableLiveData<Boolean>()
    private val _content: MutableLiveData<String> = MutableLiveData()

    val content: LiveData<String>
        get() = _content

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun fetchData() {
        launchDataLoad {
            val firstWizard = wizardRepository.fetchWizards().first()
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
        viewModelJob.cancel()
    }
}