package io.smallant.wizard.ui.features.home

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import io.smallant.wizard.R
import io.smallant.wizard.data.models.houses.HogwartsHouse
import io.smallant.wizard.ui.base.BaseRecyclerAdapter

class HousesRecyclerAdapter(itemClickListener: OnItemClickListener<HogwartsHouse>) :
    BaseRecyclerAdapter<HogwartsHouse>(itemClickListener) {

    override fun getLayoutId(item: HogwartsHouse, position: Int): Int =
        if (position % 2 == 0) R.layout.list_item_house else R.layout.list_item_house_reverse

    override fun editBinding(parent: ViewGroup, binding: ViewDataBinding) {
        var height = binding.root.context.resources
            .getDimension(R.dimen.height_item_house_superior_four)
            .toInt()

        val width = parent.measuredWidth

        if (itemCount <= 4)
            height = parent.measuredHeight / itemCount

        binding.root.layoutParams = RecyclerView.LayoutParams(width, height)
    }
}