package io.smallant.wizard.data.models.houses

import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Wizard

open class HowgwartHouseInfo {
    open val id: Int? = null
    open val name: String? = null
    var image: String? = null
}

sealed class HowgwartHouse(private val house: House, val founder: Wizard) : HowgwartHouseInfo() {
    override val name: String
        get() = house.asString()

    val founderName: String
        get() = "${founder.firstname} ${founder.lastname}"

    abstract val commonRoom: String
    abstract val animal: String

    companion object {
        val houses: List<HowgwartHouse> = listOf(
            Gryffindor(),
            Hufflepuff(),
            Ravenclaw(),
            Slytherin()
        )
    }
}

class Gryffindor : HowgwartHouse(House.GRYFFINDOR, Wizard("Godric", "Gryffindor", Sexe.MALE)) {
    override val id: Int = 1
    override val commonRoom: String = "Gryffindor Tower"
    override val animal: String = "Lion"
}

class Hufflepuff : HowgwartHouse(House.HUFFLEPUFF, Wizard("Helga", "Hufflepuff", Sexe.FEMALE)) {
    override val id: Int = 3
    override val commonRoom: String = "Hufflepuff Basement"
    override val animal: String = "Badger"
}

class Ravenclaw : HowgwartHouse(House.RAVENCLAW, Wizard("Rowena", "Ravenclaw", Sexe.FEMALE)) {
    override val id: Int = 4
    override val commonRoom: String = "Ravenclaw Tower"
    override val animal: String = "Eagle"
}

class Slytherin : HowgwartHouse(House.SLYTHERIN, Wizard("Salazar", "Slytherin", Sexe.MALE)) {
    override val id: Int = 2
    override val commonRoom: String = "Slytherin Dungeon"
    override val animal: String = "Snake"
}