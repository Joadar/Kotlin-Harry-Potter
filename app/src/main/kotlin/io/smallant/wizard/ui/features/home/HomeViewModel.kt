package io.smallant.wizard.ui.features.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.houses.HogwartsHouse
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _houses: MutableLiveData<List<HogwartsHouse>> = MutableLiveData()
    private val _spinner = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()

    val houses: LiveData<List<HogwartsHouse>>
        get() = _houses

    val spinner: LiveData<Boolean>
        get() = _spinner

    val error: LiveData<String>
        get() = _error

    fun loadHouses() {
        uiScope.launch {
            try {
                _error.value = null
                _spinner.value = true
                _houses.value = wizardRepository.fetchHouses()
            } catch (exception: Exception) {
                _houses.value = null
                _error.value = exception.message
            } finally {
                _spinner.value = false
            }
        }
    }
}