package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.DataSource
import kotlinx.coroutines.delay
import java.io.IOException

class RemoteDataSource(private val apiService: WizardService) : DataSource {
    override suspend fun fetchWizards(): Result<List<Wizard>> {
        delay(1000) // fake loading delay
        val response = apiService.getWizards().await()
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return Result.Error(IOException("Error occurred during fetching wizards!"))
        } catch (exception: Exception) {
            return Result.Error(IOException("Unable to fetch wizards"))
        }
    }
}