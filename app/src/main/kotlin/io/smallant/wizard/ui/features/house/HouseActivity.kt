package io.smallant.wizard.ui.features.house

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.smallant.wizard.R
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.databinding.ActivityHouseBinding
import io.smallant.wizard.extensions.getHogwartsHouseActionBarIcon
import io.smallant.wizard.extensions.getHogwartsHouseTheme
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.ui.base.BaseRecyclerAdapter
import io.smallant.wizard.ui.features.student.StudentActivity
import io.smallant.wizard.utils.*

class HouseActivity : BaseActivity<ActivityHouseBinding, HouseViewModel>(),
    BaseRecyclerAdapter.OnItemClickListener<Wizard> {

    private val wizardsAdapter: WizardsRecyclerAdapter = WizardsRecyclerAdapter(this)

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

        Theme.setCurrentHouseTheme(houseName)
        setTheme(Theme.houseTheme)

        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@HouseActivity.getViewModel()
            setLifecycleOwner(this@HouseActivity)
        }

        supportActionBar?.run {
            this.title = houseName
            this.setDisplayHomeAsUpEnabled(true)
            this.setDisplayShowHomeEnabled(true)
            this.setDisplayUseLogoEnabled(true)
            this.setLogo(houseName.getHogwartsHouseActionBarIcon())
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

    override fun onItemClick(item: Wizard) {
        val intent = Intent(this, StudentActivity::class.java).apply {
            putExtra(STUDENT_ID, item.id)
            putExtra(STUDENT_FULLNAME, item.fullname)
        }
        startActivity(intent)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}