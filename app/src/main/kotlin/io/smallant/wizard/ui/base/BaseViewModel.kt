package io.smallant.wizard.ui.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.utils.Event

abstract class BaseViewModel(application: Application) : AndroidViewModel(application) {

    private val _toastMessage = MutableLiveData<Event<String>>()

    val toastMessage: LiveData<Event<String>>
        get() = _toastMessage

    protected fun sendToast(message: String) {
        _toastMessage.value = Event(message)
    }

    fun onToastShown() {
        _toastMessage.value = null
    }
}