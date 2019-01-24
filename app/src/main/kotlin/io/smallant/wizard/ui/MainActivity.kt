package io.smallant.wizard.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.smallant.wizard.R
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource
import io.smallant.wizard.data.sources.remote.Result
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    private lateinit var wizardRepository: WizardRepository
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO DI
        wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))

        // TODO ViewModel
        var message: String
        job = GlobalScope.launch {
            val result = wizardRepository.fetchWizards()
            message = when (result) {
                is Result.Success -> "First wizard fullname = ${result.data[0].fullname}"
                is Result.Error -> "Exception = ${result.exception.message}"
            }
            Log.d("MainActivityLog", message)
            content.text = message
        }
    }

    override fun onDestroy() {
        job?.cancel()
        super.onDestroy()
    }
}
