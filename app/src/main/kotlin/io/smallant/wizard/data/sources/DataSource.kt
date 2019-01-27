package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.HowgwartHouseInfo
import io.smallant.wizard.data.sources.remote.Result

interface DataSource {

    /* Houses */
    suspend fun fetchHouses(): Result<List<HowgwartHouseInfo>>
    suspend fun fetchWizardsFromHouse(houseId: Int): Result<List<Wizard>>

    /* Wizards */
    suspend fun fetchWizards(): Result<List<Wizard>>
    suspend fun fetchWizard(id: Int): Result<Wizard>
}