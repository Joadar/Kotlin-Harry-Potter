package io.smallant.wizard.ui

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.Result
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.*

class MainViewModel(application: Application) : BaseViewModel(application) {

    private val viewModelJob: Job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))
    private val _content: MutableLiveData<String> = MutableLiveData()

    val content: LiveData<String>
        get() = _content

    fun fetchData() {
        uiScope.launch {
            _content.value = "Loading..."
            val message: String
            val result = withContext(Dispatchers.IO) {
                wizardRepository.fetchWizards()
            }

            message = when (result) {
                is Result.Success -> result.data[0].fullname
                is Result.Error -> result.exception.message ?: "Error on fetching wizards"
            }
            _content.value = message
            sendToast("Fetching wizards finished!")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}