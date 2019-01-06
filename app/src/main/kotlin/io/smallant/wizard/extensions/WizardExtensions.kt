package io.smallant.wizard.extensions

import io.smallant.wizard.characters.Wizard
import io.smallant.wizard.spells.JinxSpell
import io.smallant.wizard.spells.Spell

fun Wizard.spell(opponent: Wizard, spell: Spell) {
    if (spell is JinxSpell) {
        val finalDamages = this.power * spell.dammage
        opponent.life = opponent.life - finalDamages
    }
}