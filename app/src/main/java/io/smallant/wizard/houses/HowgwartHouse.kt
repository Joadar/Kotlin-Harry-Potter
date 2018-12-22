package io.smallant.wizard.houses

import io.smallant.wizard.characters.Sexe
import io.smallant.wizard.characters.Wizard

sealed class HowgwartHouse(private val house: House, val founder: Wizard) {
    val name: String
        get() = house.toString().toLowerCase().capitalize()

    val founderName: String
        get() = "${founder.firstname} ${founder.lastname}"

    abstract val commonRoom: String
    abstract val animal: String

    companion object {
        val houses: List<HowgwartHouse> = listOf(Gryffindor(), Hufflepuff(), Ravenclaw(), Slytherin())
    }
}
class Gryffindor : HowgwartHouse(House.GRYFFINDOR, Wizard("Godric", "Gryffindor", Sexe.MALE)) {
    override val commonRoom: String = "Gryffindor Tower"
    override val animal: String = "Lion"
}
class Hufflepuff : HowgwartHouse(House.HUFFLEPUFF, Wizard("Helga", "Hufflepuff", Sexe.FEMALE)) {
    override val commonRoom: String = "Hufflepuff Basement"
    override val animal: String = "Badger"
}
class Ravenclaw : HowgwartHouse(House.RAVENCLAW, Wizard("Rowena", "Ravenclaw", Sexe.FEMALE)) {
    override val commonRoom: String = "Ravenclaw Tower"
    override val animal: String = "Eagle"
}
class Slytherin : HowgwartHouse(House.SLYTHERIN, Wizard("Salazar", "Slytherin", Sexe.MALE)) {
    override val commonRoom: String = "Slytherin Dungeon"
    override val animal: String = "Snake"
}