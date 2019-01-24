package io.smallant.wizard.data.models.school

class Course(val name: String) {
    var duration: Int? = null

    operator fun component1(): String {
        return name
    }

    operator fun component2(): Int? {
        return duration
    }
}