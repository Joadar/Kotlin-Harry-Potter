package io.smallant.wizard.school

import io.smallant.wizard.characters.Student
import io.smallant.wizard.houses.HowgwartHouse

object SortingHat {
    fun sort(student: Student, defaultHouse: HowgwartHouse? = null) {
        if(defaultHouse != null)
            student.house = defaultHouse
        else
            student.house = HowgwartHouse.houses.random()
    }
}