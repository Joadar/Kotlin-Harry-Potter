package io.smallant.wizard.utils

import androidx.annotation.StyleRes
import io.smallant.wizard.R
import io.smallant.wizard.extensions.getHogwartsHouseTheme

object Theme {
    @StyleRes
    var houseTheme: Int = R.style.AppTheme

    fun setCurrentHouseTheme(houseName: String?) {
        houseTheme = houseName.getHogwartsHouseTheme()
    }

}