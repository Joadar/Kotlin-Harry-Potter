package io.smallant.wizard

import io.smallant.wizard.characters.Wizard
import java.math.RoundingMode
import kotlin.random.Random

class Wand(val wood: Wood, val core: Core, val length: Double) {
    enum class Wood {
        ACACIA, ALDER, APPLE, ASH, ASPEN, BEECH, BLACKTHORN, BLACK_WALNUT,
        CEDAR, CHERRY, CHESTNUT, CYPRESS, DOGWOOD, EBONY, ENGLISH_OAK, ELDER, ELM,
        FIR, HAWTHORN, HAZEL, HOLLY, HORNBEAM, LARCH, LAUREL, MAPLE, PEAR, PINE, POPLAR,
        RED_OAK, REDWOOD, REED, ROSEWOOD, ROWAN, SILVER_LIME, SPRUCE, SNAKEWOOD, SUGAR_MAPLE,
        SYCAMORE, TAMARACK, VINE, WALNUT, WILLOW, YEW
    }

    enum class Core {
        UNICORN_HAIR, DRAGON_HEARTSTRING, PHOENIX_FEATHER, VEELA_HAIR, THESTRAL_TAIL_HAIR,
        TROLL_WHISKER, KELPIE_HAIR, THUNDERBIRD_TAIL_FEATHER, WAMPUS_CAT_HAIR, WHITE_RIVER_MONSTER_SPINE,
        ROUGAROU_HAIR, KNEAZLE_WHISKERS, HORNED_SERPENT_HORN, SNALLYGASTER_HEARTSRING,
        JACKALOPE_ANTLER, BASILIK_HORN
    }

    fun chooseWizard(wizard: Wizard) {
        wizard.wand = this
    }

    companion object {

        private val listOfWands: ArrayList<Wand> = arrayListOf()

        init {
            listOfWands()
        }

        private fun listOfWands() {
            Wood.values().map { wood ->
                Core.values().map { core ->
                    listOfWands.add(
                        Wand(
                            wood,
                            core,
                            Random.nextDouble(9.0, 20.0)
                                .toBigDecimal()
                                .setScale(1, RoundingMode.FLOOR)
                                .toDouble()
                        )
                    )
                }
            }
        }

        fun chooseWizard(wizard: Wizard) {
            if (wizard.fullname == "Harry Potter")
                wizard.wand = Wand(Wood.HOLLY, Core.PHOENIX_FEATHER, 11.0)
            else
                wizard.wand = listOfWands.random()
        }
    }
}