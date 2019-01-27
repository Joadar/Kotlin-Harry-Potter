package io.smallant.wizard.data.models.characters

import io.smallant.wizard.data.models.houses.HogwartsHouse
import io.smallant.wizard.data.models.school.Course

class Professor(firstname: String, lastname: String, sexe: Sexe, val house: HogwartsHouse, val course: Course) :
    Wizard(firstname, lastname, sexe) {

    enum class Position {
        NORMAL, HEAD_OF_HOUSE, HEADMASTER
    }

    private var status: Position? = null
    val position: String?
        get() = when (status) {
            Position.HEAD_OF_HOUSE -> "Head of ${house.name}"
            Position.HEADMASTER -> "Headmaster"
            else -> null
        }

    fun changePosition(status: Position) {
        this.status = status
    }
}