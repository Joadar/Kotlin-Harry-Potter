package io.smallant.wizard.data.sources

import io.mockk.clearMocks
import io.mockk.coEvery
import io.mockk.mockk
import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.Result
import io.smallant.wizard.data.sources.remote.WizardService
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException

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
        coEvery { wizardService.getWizard(-1).await() }.coAnswers {
            Response.error(
                400,
                ResponseBody.create(
                    MediaType.parse("application/json"),
                    "{\"message\": \"Bad Request\"}"
                )
            )
        }
    }

    @Test
    fun `fetch wizards should work`() = runBlocking {
        repository.fetchWizards().first().let { firstWizard ->
            assertEquals("John", firstWizard.firstname)
            assertEquals("Doe", firstWizard.lastname)
        }
    }

    @Test
    fun `fetch specific wizard should work`() = runBlocking {
        with(repository.fetchWizard(1)) {
            assertEquals("Jeanne Doe", fullname)
        }
    }

    @Test(expected = IOException::class)
    fun `fetch specific wizard should throw an IOException`() {
        runBlocking {
            repository.fetchWizard(-1)
        }
    }

    @Test
    fun `fetch specific wizard should fail`() {
        val remote: RemoteDataSource = mockk()
        coEvery { remote.fetchWizard(0) }.coAnswers {
            Result.Error(IOException("Error fetching wizard 0"))
        }
        runBlocking {
            val result: Result.Error = remote.fetchWizard(0) as Result.Error
            assertEquals(IOException("Error fetching wizard 0").message, result.exception.message)
        }
    }
}