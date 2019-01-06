package io.smallant.wizard.extensions

import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.spells.JinxSpell
import io.smallant.wizard.data.models.spells.Spell

fun Wizard.spell(opponent: Wizard, spell: Spell) {
    if (spell is JinxSpell) {
        val finalDamages = this.power * spell.dammage
        opponent.life = opponent.life - finalDamages
    }
}