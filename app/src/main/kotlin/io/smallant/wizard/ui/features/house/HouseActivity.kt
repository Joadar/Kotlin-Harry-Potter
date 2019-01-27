package io.smallant.wizard.ui.features.house

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import io.smallant.wizard.R
import io.smallant.wizard.databinding.ActivityHouseBinding
import io.smallant.wizard.ui.base.BaseActivity
import io.smallant.wizard.utils.HOUSE_ID
import io.smallant.wizard.utils.HOUSE_NAME

class HouseActivity : BaseActivity<ActivityHouseBinding, HouseViewModel>() {

    override fun getLayoutId(): Int = R.layout.activity_house
    override fun getViewModel(): HouseViewModel = ViewModelProviders.of(this).get(HouseViewModel::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var houseName: String? = getString(R.string.howgwart)
        var houseId: Int = 0

        intent?.extras?.let { bundle ->
            houseId = bundle.getInt(HOUSE_ID, 0)
            houseName = bundle.getString(HOUSE_NAME)
        }

        with(supportActionBar) {
            this?.title = houseName
            this?.setDisplayHomeAsUpEnabled(true)
        }

        if(savedInstanceState == null) {

        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}