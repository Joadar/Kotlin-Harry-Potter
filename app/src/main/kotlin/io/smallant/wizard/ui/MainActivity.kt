package io.smallant.wizard.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import io.smallant.wizard.R
import io.smallant.wizard.extensions.listenEvent

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.toastMessage.listenEvent(this) {
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}
