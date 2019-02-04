package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.HogwartsHouseInfo
import io.smallant.wizard.data.sources.DataSource
import kotlinx.coroutines.delay
import retrofit2.Response
import java.io.IOException

class RemoteDataSource(private val apiService: WizardService) : DataSource {

    /* Houses */
    override suspend fun fetchHouses(): Result<List<HogwartsHouseInfo>> {
        return manageResponse { apiService.getHouses().await() }
    }

    override suspend fun fetchWizardsFromHouse(houseId: Int): Result<List<Wizard>> {
        delay(500) // fake loading delay
        return manageResponse { apiService.getWizardsFromHouse(houseId).await() }
    }

    /* Wizards */
    override suspend fun fetchWizards(): Result<List<Wizard>> {
        delay(1000) // fake loading delay
        return manageResponse { apiService.getWizards().await() }
    }

    override suspend fun fetchWizard(id: Int): Result<Wizard> {
        return manageResponse { apiService.getWizard(id).await() }
    }

    override suspend fun fetchWizardFriends(id: Int): Result<List<Wizard>> {
        return manageResponse { apiService.getWizardFriends(id).await() }
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