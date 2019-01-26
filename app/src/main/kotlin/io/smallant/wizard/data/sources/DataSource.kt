package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.Result
import kotlinx.coroutines.Deferred

interface DataSource {
    suspend fun fetchWizards(): Result<List<Wizard>>
    suspend fun fetchWizard(id: Int): Result<Wizard>
}