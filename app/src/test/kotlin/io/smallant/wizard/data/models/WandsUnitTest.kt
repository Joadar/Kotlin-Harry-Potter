package io.smallant.wizard.data.models

import org.junit.Assert.assertEquals
import org.junit.Test

class WandsUnitTest {

    private val wand = Wand(Wand.Wood.DOGWOOD, Wand.Core.ROUGAROU_HAIR, 20.0)

    @Test
    fun `is number of wand's wood correct`() {
        assertEquals(43, Wand.Wood.values().size)
    }

    @Test
    fun `is number of wand's core correct`() {
        assertEquals(16, Wand.Core.values().size)
    }

    @Test
    fun `is number of number wands correct`() {
        val nbWands = Wand.Wood.values().size * Wand.Core.values().size
        assertEquals(688, nbWands)
    }

    @Test
    fun `is wand correct`() {
        with(wand) {
            assertEquals(Wand.Wood.DOGWOOD, wood)
            assertEquals(Wand.Core.ROUGAROU_HAIR, core)
            assertEquals(20.0, length, 0.001)
        }
    }
}