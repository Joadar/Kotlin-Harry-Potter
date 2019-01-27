package io.smallant.wizard.ui.features.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import io.smallant.wizard.R
import io.smallant.wizard.data.models.houses.HowgwartHouse
import io.smallant.wizard.databinding.ActivityHomeBinding
import io.smallant.wizard.extensions.listenEvent
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.ui.base.BaseRecyclerAdapter
import io.smallant.wizard.ui.features.house.HouseActivity
import io.smallant.wizard.utils.HOUSE_ID
import io.smallant.wizard.utils.HOUSE_NAME

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(), BaseRecyclerAdapter.OnItemClickListener<HowgwartHouse> {

    private val housesAdapter: HousesRecyclerAdapter = HousesRecyclerAdapter(this)

    override fun getLayoutId(): Int = R.layout.activity_home
    override fun getViewModel(): HomeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
    override fun getRootLayout() = findViewById<View>(R.id.rootLayout)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding?.apply {
            viewModel = this@HomeActivity.getViewModel()
            setLifecycleOwner(this@HomeActivity)
        }

        if (savedInstanceState == null) {
            getViewModel().loadHouses()
        }

        viewDataBinding?.recyclerHouses?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@HomeActivity)
            if (adapter == null) {
                adapter = housesAdapter
            }
        }

        getViewModel().houses.observe(this, Observer { houses ->
            housesAdapter.setItems(houses)
        })
    }

    override fun onItemClick(item: HowgwartHouse) {
        val intent = Intent(this@HomeActivity, HouseActivity::class.java)
        intent.putExtra(HOUSE_ID, item.id)
        intent.putExtra(HOUSE_NAME, item.name)
        startActivity(intent)
    }
}
