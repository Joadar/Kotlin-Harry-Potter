package io.smallant.wizard.data.models.houses

enum class House {
    GRYFFINDOR, HUFFLEPUFF, RAVENCLAW, SLYTHERIN;
}

fun House.asString(): String = this.toString().toLowerCase().capitalize()
