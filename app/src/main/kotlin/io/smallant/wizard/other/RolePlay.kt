package io.smallant.wizard.other

object RolePlay {
    fun onDuel(block: () -> Unit) {
        println("Before the duel")
        block()
        println("After the duel")
    }
}