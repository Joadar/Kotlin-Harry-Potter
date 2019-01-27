package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.HowgwartHouseInfo
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WizardService {

    @GET("/wizards")
    fun getWizards(): Deferred<Response<List<Wizard>>>

    @GET("/wizards/{id}")
    fun getWizard(@Path("id") id: Int): Deferred<Response<Wizard>>

    @GET("/houses")
    fun getHouses(): Deferred<Response<List<HowgwartHouseInfo>>>
}