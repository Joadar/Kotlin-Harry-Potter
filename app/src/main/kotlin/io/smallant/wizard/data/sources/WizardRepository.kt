package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WizardRepository(private val remoteDataSource: RemoteDataSource) {

    suspend fun fetchWizards(): List<Wizard> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchWizards().await()
        }
    }

    suspend fun fetchWizard(id: Int): Wizard {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchWizard(id).await()
        }
    }

}