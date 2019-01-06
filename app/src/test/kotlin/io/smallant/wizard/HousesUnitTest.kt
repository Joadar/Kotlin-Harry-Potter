package io.smallant.wizard

import io.smallant.wizard.houses.Gryffindor
import io.smallant.wizard.houses.House
import io.smallant.wizard.school.Enemy
import org.junit.Assert.assertEquals
import org.junit.Test

class HousesUnitTest {

    private val gryffindor: Gryffindor = Gryffindor()

    @Test
    fun `is house's founder correct`() {
        assertEquals("Godric Gryffindor", gryffindor.founderName)
    }

    @Test
    fun `is house's enemy correct`() {
        val houseEnemy = Enemy.house(gryffindor)
        assertEquals(House.SLYTHERIN, houseEnemy)
    }

    @Test
    fun `is houses number correct`() {
        val housesNumber = House.values().size
        assertEquals(4, housesNumber)
    }
}