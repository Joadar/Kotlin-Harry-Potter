package io.smallant.wizard.data.models.school

import io.smallant.wizard.data.models.characters.Student
import io.smallant.wizard.data.models.houses.HowgwartHouse

object SortingHat {
    fun sort(student: Student, defaultHouse: HowgwartHouse? = null) {
        if(defaultHouse != null)
            student.house = defaultHouse
        else
            student.house = HowgwartHouse.houses.random()
    }
}