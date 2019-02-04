package io.smallant.wizard.ui.features.student

import android.app.Application
import android.util.Log
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class StudentViewModel(application: Application) : BaseViewModel(application) {

    fun fetchData(studentId: Int) {
        uiScope.launch {
            val wizardDetail: Wizard = wizardRepository.fetchWizard(studentId)
            val wizardFriends: List<Wizard> = wizardRepository.fetchWizardFriends(studentId)

            Log.d("studentVMLog", "${wizardDetail.fullname} has ${wizardFriends.size} friends")
        }
    }

}