package io.smallant.wizard.ui.features.house

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.smallant.wizard.R
import io.smallant.wizard.databinding.ActivityHouseBinding
import io.smallant.wizard.extensions.getHogwartsHouseActionBarIcon
import io.smallant.wizard.extensions.getHogwartsHouseTheme
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.utils.HOUSE_ID
import io.smallant.wizard.utils.HOUSE_NAME

class HouseActivity : BaseActivity<ActivityHouseBinding, HouseViewModel>() {

    private val wizardsAdapter: WizardsRecyclerAdapter = WizardsRecyclerAdapter()

    override fun getLayoutId(): Int = R.layout.activity_house
    override fun getViewModel(): HouseViewModel = ViewModelProviders.of(this).get(HouseViewModel::class.java)
    override fun getRootLayout() = findViewById<View>(R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {

        var houseName: String? = getString(R.string.hogwarts)
        var houseId = 0

        intent?.extras?.let { bundle ->
            houseId = bundle.getInt(HOUSE_ID, 0)
            houseName = bundle.getString(HOUSE_NAME)
        }

        val theme: Int = houseName.getHogwartsHouseTheme()
        setTheme(theme)

        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@HouseActivity.getViewModel()
            setLifecycleOwner(this@HouseActivity)
        }

        with(supportActionBar) {
            this?.title = houseName
            this?.setDisplayHomeAsUpEnabled(true)
            this?.setDisplayShowHomeEnabled(true)
            this?.setDisplayUseLogoEnabled(true)
            this?.setLogo(houseName.getHogwartsHouseActionBarIcon())
        }

        if (savedInstanceState == null) {
            getViewModel().fetchData(houseId)
        }

        viewDataBinding?.recyclerWizards?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HouseActivity)
            if (adapter == null) {
                adapter = wizardsAdapter
            }
        }

        getViewModel().wizards.observe(this, Observer { wizards ->
            wizardsAdapter.setItems(wizards)
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}