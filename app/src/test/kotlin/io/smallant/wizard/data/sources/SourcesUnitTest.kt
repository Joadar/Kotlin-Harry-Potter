package io.smallant.wizard.data.sources

import io.mockk.clearMocks
import io.mockk.every
import io.mockk.mockk
import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.WizardService
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class SourcesUnitTest {

    private val wizardService: WizardService = mockk()
    private val repository: WizardRepository = run {
        val remoteDataSource = RemoteDataSource(wizardService)
        WizardRepository(remoteDataSource)
    }

    @Before
    fun init() {
        clearMocks(wizardService)
    }

    @Test
    fun `fetch wizards should works`() {
        // TODO fix this test
        every { repository.fetchWizards() } returns listOf(Wizard("John", "Doe", Sexe.MALE))
        repository.fetchWizards()[0].let { firstWizard ->
            assertEquals("John", firstWizard.firstname)
            assertEquals("Doe", firstWizard.lastname)
        }
    }

    @Test
    fun `fetch wizard should not work`() {
        val exception: Exception = Exception()
        //every { repository.fetchWizards() } returns exception
    }

}