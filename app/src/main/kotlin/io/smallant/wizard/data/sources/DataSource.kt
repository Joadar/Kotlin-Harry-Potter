package io.smallant.wizard.data.sources

import io.smallant.wizard.data.models.characters.Wizard

interface DataSource {
    fun fetchWizards(): List<Wizard>
}