package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource

class WizardRepository(private val remoteDataSource: RemoteDataSource) : DataSource {
    override fun fetchWizards(): List<Wizard> {
        return remoteDataSource.fetchWizards()
    }
}