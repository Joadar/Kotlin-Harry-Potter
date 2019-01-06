package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.DataSource
import kotlinx.coroutines.runBlocking

class RemoteDataSource(private val apiService: WizardService) : DataSource {
    override fun fetchWizards(): List<Wizard> = runBlocking {
        apiService.getWizards().await()
    }
}