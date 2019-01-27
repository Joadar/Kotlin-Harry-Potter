package io.smallant.wizard.ui.features.house

import android.os.Bundle
import android.util.Log
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
        var houseName: String? = "Howgwart"
        intent?.extras?.let { bundle ->
            val houseId = bundle.getInt(HOUSE_ID, 0)
            houseName = bundle.getString(HOUSE_NAME)
            Log.d("HouseLog", "$houseName | houseId selected = $houseId")
        }
        //setSupportActionBar(findViewById(R.id.toolbar))
        with(supportActionBar) {
            this?.title = houseName
            this?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}