package io.smallant.wizard.characters

import io.smallant.wizard.houses.HowgwartHouse


class Student(firstname: String, lastname: String, sexe: Sexe): Wizard(firstname, lastname, sexe) {
    var house: HowgwartHouse? = null
}