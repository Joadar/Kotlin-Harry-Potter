package io.smallant.wizard.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import io.smallant.wizard.test.util.captureValues
import io.smallant.wizard.ui.features.home.HomeViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var subject: HomeViewModel = HomeViewModel(mockk())

    @Test
    fun whenSuccessfulLoadData_ShowAndHideSpinner() = runBlocking {
        subject.spinner.captureValues {
            subject.loadHouses()
            assertSendsValues(1_000, true, false)
        }
    }
}