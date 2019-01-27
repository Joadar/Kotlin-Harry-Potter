package io.smallant.wizard.data.sources

import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.WizardService
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class SourcesUnitTest {

    private val wizardService: WizardService = mockk()
    private val repository: WizardRepository = run {
        val remoteDataSource = RemoteDataSource(wizardService)
        WizardRepository(remoteDataSource)
    }

    @Before
    fun init() {
        clearMocks(wizardService)
        coEvery { wizardService.getWizards().await() } coAnswers {
            Response.success(
                listOf(
                    Wizard(
                        "John",
                        "Doe",
                        Sexe.MALE
                    )
                )
            )
        }
        coEvery { wizardService.getWizard(1).await() } coAnswers {
            Response.success(Wizard("Jeanne", "Doe", Sexe.FEMALE))
        }
    }

    @Test
    fun `fetch wizards should work`() {
        runBlocking {
            repository.fetchWizards().first().let { firstWizard ->
                assertEquals("John", firstWizard.firstname)
                assertEquals("Doe", firstWizard.lastname)
            }
        }
    }

    @Test
    fun `fetch specific wizard should work`() {
        runBlocking {
            with(repository.fetchWizard(1)) {
                assertEquals("Jeanne Doe", fullname)
            }
        }
    }
}