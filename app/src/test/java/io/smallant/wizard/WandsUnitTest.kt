package io.smallant.wizard

import org.junit.Assert.assertEquals
import org.junit.Test

class WandsUnitTest {

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
}