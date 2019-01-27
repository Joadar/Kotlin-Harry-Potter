package io.smallant.wizard.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.utils.Event
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelChildren

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _toastMessage = MutableLiveData<Event<String>>()
    private val _snackbarMessage = MutableLiveData<Event<String>>()

    private val viewModelJob: Job = Job()
    protected val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    protected val wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    val snackbarMessage: LiveData<Event<String>>
        get() = _snackbarMessage

    override fun onCleared() {
        super.onCleared()
        uiScope.coroutineContext.cancelChildren()
    }

    protected fun sendToast(message: String) {
        _toastMessage.value = Event(message)
    }

    protected fun sendSnackbar(message: String) {
        _snackbarMessage.value = Event(message)
    }

    fun onToastShown() {
        _toastMessage.value = null
    }

    fun onSnackbarShown() {
        _snackbarMessage.value = null
    }
}