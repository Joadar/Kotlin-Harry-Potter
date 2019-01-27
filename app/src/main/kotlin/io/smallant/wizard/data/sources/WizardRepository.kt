package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.*
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.await
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WizardRepository(private val remoteDataSource: RemoteDataSource) {

    /* Houses */
    suspend fun fetchHouses(): List<HogwartsHouse> {
        return withContext(Dispatchers.IO) {
            val houses = remoteDataSource.fetchHouses().await()
            houses.map {
                val hogwartsHouse = when (it.name) {
                    House.SLYTHERIN.asString() -> Slytherin()
                    House.RAVENCLAW.asString() -> Ravenclaw()
                    House.HUFFLEPUFF.asString() -> Hufflepuff()
                    else -> Gryffindor()
                }
                hogwartsHouse.image = it.image
                hogwartsHouse
            }
        }
    }

    suspend fun fetchWizardsFromHouse(houseId: Int) : List<Wizard> {
        return withContext(Dispatchers.IO) {
            remoteDataSource.fetchWizardsFromHouse(houseId).await()
        }
    }

    /* Wizards */
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