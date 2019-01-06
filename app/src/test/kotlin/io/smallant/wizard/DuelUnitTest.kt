package io.smallant.wizard

import io.smallant.wizard.characters.Professor
import io.smallant.wizard.characters.Sexe
import io.smallant.wizard.characters.Student
import io.smallant.wizard.extensions.spell
import io.smallant.wizard.houses.Slytherin
import io.smallant.wizard.school.Course
import io.smallant.wizard.spells.CounterSpell
import io.smallant.wizard.spells.JinxSpell
import io.smallant.wizard.spells.Spell
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class DuelUnitTest {

    private val harryPotter = Student("Harry", "Potter", Sexe.MALE)
    private val severusSnape = Professor("Severus", "Snape", Sexe.MALE, Slytherin(), Course("Potion"))

    private val sectumsempraSpell: Spell = JinxSpell("Sectumsempra", 40)
    private val protegoSpell: Spell = CounterSpell("Protego", 30)

    @Before
    fun setUp() {
        harryPotter.power = 1.1
        severusSnape.power = 1.6
    }

    @Test
    fun `is Harry Potter loosing life`() {
        severusSnape.spell(harryPotter, sectumsempraSpell)
        assertEquals(36.0, harryPotter.life, 0.001)
    }

    @Test
    fun `is Harry Potter's dammage correct`() {
        //val finalDammages = harryPotter.power * protegoSpell
        //assertEquals(11.0, finalDammages, 0.001)
    }

}