package io.smallant.wizard.ui.features.student

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.launch
import java.io.IOException

class StudentViewModel(application: Application) : BaseViewModel(application) {

    private var _spinner: MutableLiveData<Boolean> = MutableLiveData()

    val spinner: LiveData<Boolean>
        get() = _spinner

    fun fetchData(studentId: Int) {
        uiScope.launch {
            try {
                _spinner.value = true
                val wizardDetail: Wizard = wizardRepository.fetchWizard(studentId)
                val wizardFriends: List<Wizard> = wizardRepository.fetchWizardFriends(studentId)
                Log.d("studentVMLog", "${wizardDetail.fullname} has ${wizardFriends.size} friends")
            } catch (exception: IOException) {
            } finally {
                _spinner.value = false
            }
        }
    }

}