package io.smallant.wizard.ui.features.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.houses.HowgwartHouse
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.io.IOException

class HomeViewModel(application: Application) : BaseViewModel(application) {

    private val _spinner = MutableLiveData<Boolean>()
    private val _houses: MutableLiveData<List<HowgwartHouse>> = MutableLiveData()

    val houses: LiveData<List<HowgwartHouse>>
        get() = _houses

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun loadHouses() {
        uiScope.launch {
            try {
                _spinner.value = true
                _houses.value = wizardRepository.fetchHouses()
            } catch (exception: IOException) {
                _houses.value = null
            } finally {
                _spinner.value = false
            }
        }
    }
}