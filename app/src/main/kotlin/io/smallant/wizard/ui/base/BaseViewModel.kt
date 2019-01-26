package io.smallant.wizard.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.utils.Event

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _toastMessage = MutableLiveData<Event<String>>()
    private val _snackbarMessage = MutableLiveData<Event<String>>()

    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    val snackbarMessage: LiveData<Event<String>>
        get() = _snackbarMessage

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