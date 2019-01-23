package io.smallant.wizard.data.models

import io.smallant.wizard.data.models.characters.Professor
import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Student
import io.smallant.wizard.data.models.houses.Slytherin
import io.smallant.wizard.data.models.school.Course
import io.smallant.wizard.data.models.spells.CounterSpell
import io.smallant.wizard.data.models.spells.JinxSpell
import io.smallant.wizard.data.models.spells.Spell
import io.smallant.wizard.extensions.spell
import io.smallant.wizard.other.RolePlay
import org.junit.Assert.assertEquals
import org.junit.Test

class DuelUnitTest {

    private val harryPotter = Student("Harry", "Potter", Sexe.MALE)
        .apply {
            power = 1.1
        }
    private val severusSnape = Professor("Severus", "Snape", Sexe.MALE, Slytherin(), Course("Potion"))
        .apply {
            power = 1.6
        }

    private val sectumsempraSpell: Spell = JinxSpell("Sectumsempra", 40)
    private val protegoSpell: Spell = CounterSpell("Protego", 30)

    @Test
    fun `is Harry Potter loosing life`() {
        severusSnape.spell(harryPotter, sectumsempraSpell)
        assertEquals(36.0, harryPotter.life, 0.001)
    }

    @Test
    fun `is Harry Potter fighting back`() {
        RolePlay.onDuel {
            println("The duel is just beginning")
            severusSnape.spell(harryPotter, sectumsempraSpell)
                .also {
                    harryPotter.spell(severusSnape, sectumsempraSpell)
                }
            println("The duel is just ending")
        }
        assertEquals(36.0, harryPotter.life, 0.001)
        assertEquals(56.0, severusSnape.life, 0.001)
    }

    @Test
    fun `is Harry Potter's dammage correct`() {
        //val finalDammages = harryPotter.power * protegoSpell
        //assertEquals(11.0, finalDammages, 0.001)
    }

}