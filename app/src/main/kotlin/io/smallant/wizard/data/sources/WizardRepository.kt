package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.Result

class WizardRepository(private val remoteDataSource: RemoteDataSource) : DataSource {
    override suspend fun fetchWizards(): Result<List<Wizard>> {
        return remoteDataSource.fetchWizards()
    }
}