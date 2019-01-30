package io.smallant.wizard.ui.features.house

import io.smallant.wizard.R
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.ui.base.BaseRecyclerAdapter

class WizardsRecyclerAdapter : BaseRecyclerAdapter<Wizard>() {
    override fun getLayoutIdAtPosition(position: Int): Int = R.layout.list_item_wizard
}