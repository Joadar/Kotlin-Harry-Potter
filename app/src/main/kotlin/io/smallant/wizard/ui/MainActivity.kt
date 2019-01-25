package io.smallant.wizard.ui

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import io.smallant.wizard.R
import io.smallant.wizard.databinding.ActivityMainBinding
import io.smallant.wizard.ui.base.BaseActivity

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@MainActivity.getViewModel()
            setLifecycleOwner(this@MainActivity)
        }
    }
}
