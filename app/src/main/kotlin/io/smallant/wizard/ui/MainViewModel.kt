package io.smallant.wizard.ui

import android.app.Application
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.Result
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.*

class MainViewModel(application: Application) : BaseViewModel(application) {

    private var job: Job? = null

    private val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

    init {
        fetchData()
    }

    private fun fetchData() {
        var message: String
        job = CoroutineScope(Dispatchers.Main).launch {
            val result = withContext(Dispatchers.IO) {
                wizardRepository.fetchWizards()
            }

            message = when (result) {
                is Result.Success -> "First wizard fullname = ${result.data[0].fullname}"
                is Result.Error -> "Exception = ${result.exception.message}"
            }
            sendToast(message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}