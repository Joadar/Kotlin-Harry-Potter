package io.smallant.wizard.ui.features.home

import io.smallant.wizard.R
import io.smallant.wizard.data.models.houses.HowgwartHouse
import io.smallant.wizard.ui.base.BaseRecyclerAdapter

class HousesRecyclerAdapter : BaseRecyclerAdapter<HowgwartHouse>() {
    override fun getLayoutIdAtPosition(position: Int): Int =
        if (position % 2 == 0) R.layout.list_item_house else R.layout.list_item_house_reverse
}