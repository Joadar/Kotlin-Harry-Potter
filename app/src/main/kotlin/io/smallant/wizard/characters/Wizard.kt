package io.smallant.wizard.characters

import io.smallant.wizard.Wand
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

open class Wizard(val firstname: String, var lastname: String, var sexe: Sexe) {
    private var dob: Date = Date()
    val age: Int
        get() = age(dob)

    var power: Double = 1.0
    var life: Double = 100.0

    var wand: Wand? = null

    var dateOfBirth: String = ""
        set(value) {
            dob = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH).parse(value)
        }

    val fullname: String
        get() = "$firstname $lastname"

    private fun age(birthday: Date): Int = (TimeUnit.MILLISECONDS.toDays(Date().time - birthday.time) / 365).toInt()

}