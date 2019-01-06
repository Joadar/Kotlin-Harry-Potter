package io.smallant.wizard.data.sources.remote

import io.smallant.wizard.data.models.characters.Wizard
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface WizardService {
    @GET("/wizards")
    fun getWizards(): Deferred<List<Wizard>>
}