package io.smallant.wizard.data.models.school

import io.smallant.wizard.data.models.houses.*


object Enemy {
    fun house(house: HowgwartHouse) = when (house) {
        is Gryffindor -> House.SLYTHERIN
        is Hufflepuff -> House.RAVENCLAW
        is Ravenclaw -> House.HUFFLEPUFF
        is Slytherin -> House.GRYFFINDOR
    }
}