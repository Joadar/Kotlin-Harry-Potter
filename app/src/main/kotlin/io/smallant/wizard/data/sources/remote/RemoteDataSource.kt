package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.HowgwartHouse
import io.smallant.wizard.data.models.houses.HowgwartHouseInfo
import io.smallant.wizard.data.sources.DataSource
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException

class RemoteDataSource(private val apiService: WizardService) : DataSource {
    override suspend fun fetchWizards(): Result<List<Wizard>> {
        delay(1000) // fake loading delay
        return manageResponse { apiService.getWizards().await() }
    }

    override suspend fun fetchWizard(id: Int): Result<Wizard> {
        return manageResponse { apiService.getWizard(id).await() }
    }

    override suspend fun fetchHouses(): Result<List<HowgwartHouseInfo>> {
        return manageResponse { apiService.getHouses().await() }
    }

    private suspend fun <T : Any> manageResponse(result: suspend () -> Response<T>): Result<T> {
        val response = result()
        try {
            if (response.isSuccessful) {
                response.body()?.let {
                    return Result.Success(it)
                }
            }
            return Result.Error(IOException("Error occurred during fetching data!"))
        } catch (exception: Exception) {
            return Result.Error(IOException("Unable to fetch data"))
        }
    }
}