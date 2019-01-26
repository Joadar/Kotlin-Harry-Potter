package io.smallant.wizard.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import io.mockk.mockk
import io.smallant.wizard.test.util.captureValues
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class MainViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private var subject: MainViewModel = MainViewModel(mockk())

    @Test
    fun whenSuccessfulLoadData_ShowAndHideSpinner() {
        runBlocking {
            subject.spinner.captureValues {
                subject.fetchData()
                assertSendsValues(2_000, true, false)
            }
        }
    }
}