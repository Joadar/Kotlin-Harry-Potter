package io.smallant.wizard.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.smallant.wizard.R
import io.smallant.wizard.data.sources.WizardRepository
import io.smallant.wizard.data.sources.remote.APIManager
import io.smallant.wizard.data.sources.remote.RemoteDataSource

class MainActivity : AppCompatActivity() {

    private lateinit var wizardRepository: WizardRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // TODO DI
        wizardRepository = WizardRepository(RemoteDataSource(APIManager().apiWizardService))
        Log.d("MainActivity", "first wizard fullname = ${wizardRepository.fetchWizards()[0].fullname}")
    }
}
