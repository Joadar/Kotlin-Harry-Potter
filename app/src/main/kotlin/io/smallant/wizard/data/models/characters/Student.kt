package io.smallant.wizard.data.models.characters

import io.smallant.wizard.data.models.houses.HogwartsHouse


class Student(firstname: String, lastname: String, sexe: Sexe): Wizard(firstname, lastname, sexe) {
    var house: HogwartsHouse? = null
}