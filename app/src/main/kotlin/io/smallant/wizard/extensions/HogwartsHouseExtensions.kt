package io.smallant.wizard.extensions

import io.smallant.wizard.R
import io.smallant.wizard.data.models.houses.House
import io.smallant.wizard.data.models.houses.asString

fun String?.getHogwartsHouseTheme(): Int {
    return when (this) {
        House.GRYFFINDOR.asString() -> R.style.AppTheme_House_Gryffindor
        House.SLYTHERIN.asString() -> R.style.AppTheme_House_Slytherin
        House.RAVENCLAW.asString() -> R.style.AppTheme_House_Ravenclaw
        House.HUFFLEPUFF.asString() -> R.style.AppTheme_House_Hufflepuff
        else -> R.style.AppTheme
    }
}