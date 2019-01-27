package io.smallant.wizard.data.models

import io.smallant.wizard.data.models.characters.Professor
import io.smallant.wizard.data.models.characters.Sexe
import io.smallant.wizard.data.models.characters.Student
import io.smallant.wizard.data.models.characters.Wizard
import io.smallant.wizard.data.models.houses.*
import io.smallant.wizard.data.models.school.Course
import io.smallant.wizard.data.models.school.SortingHat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.roundToInt

class CharactersUnitTest {

    private val potionCourse = Course("Potion")

    private val harryPotter = Student("Harry", "Potter", Sexe.MALE)
    private val hermioneGranger = Student("Hermione", "Granger", Sexe.FEMALE)
    private val ronWeasley = Student("Ron", "Weasley", Sexe.MALE)
    private val dracoMalfoy = Student("Draco", "Malfoy", Sexe.MALE)

    private val severusSnape: Professor = potionCourse.apply {
        duration = 90
    }.also {
        println("The potion ${it.name} has a ${it.duration} minutes duration")
    }.let { potionCourse ->
        Professor("Severus", "Snape", Sexe.MALE, Slytherin(), potionCourse)
    }.also {
        println("${it.fullname} is a great professor")
    }

    private val randomWizard = Wizard("Jeanne", "Doe", Sexe.FEMALE)

    private val albusDumbledore = Professor("Albus", "Dumbledore", Sexe.MALE, Gryffindor(), Course(""))
        .apply {
            changePosition(Professor.Position.HEADMASTER)
        }

    private val hagrid: Wizard = Wizard("Rebeus", "Hagrid", Sexe.MALE)
        .apply {
            power = 5.0
        }

    private val gryffindor: Gryffindor = Gryffindor()
    private val ravenclaw: Ravenclaw = Ravenclaw()
    private val hufflepuff: Hufflepuff = Hufflepuff()
    private val slytherin: Slytherin = Slytherin()

    private val listOfWizards: List<Wizard>

    init {
        SortingHat.sort(harryPotter, gryffindor)
        SortingHat.sort(hermioneGranger, gryffindor)
        SortingHat.sort(ronWeasley, gryffindor)
        SortingHat.sort(dracoMalfoy, slytherin)

        listOfWizards = listOf(harryPotter, hermioneGranger, ronWeasley, dracoMalfoy, severusSnape, randomWizard)
    }

    @Test
    fun `is sorting correct`() {
        SortingHat.sort(harryPotter, Gryffindor())
        assertTrue(harryPotter.house is Gryffindor)
        assertTrue(harryPotter.house is HogwartsHouse)
    }

    @Test
    fun `is full name correct`() {
        assertEquals("Harry Potter", harryPotter.fullname)
        assertEquals("Severus Snape", severusSnape.fullname)
    }

    @Test
    fun `is age correct`() {
        harryPotter.dateOfBirth = "31/07/1980"
        assertEquals(38, harryPotter.age)
    }

    @Test
    fun `is new professor's position correct`() {
        assertEquals(null, severusSnape.position)
        severusSnape.changePosition(Professor.Position.HEAD_OF_HOUSE)
        assertEquals("Head of Slytherin", severusSnape.position)
    }

    @Test
    fun `is number of male and female wizards are correct`() {
        val (male, female) = listOfWizards.partition { it.sexe == Sexe.MALE }
        assertEquals(4, male.count())
        assertEquals(2, female.count())
    }

    @Test
    fun `is number of students correct`() {
        val nbStudents = listOfWizards.filter { it is Student }.count()
        assertEquals(4, nbStudents)
    }

    @Test
    fun `is number of students per house correct`() {
        @Suppress("unchecked_cast")
        val houseStudents = (listOfWizards.filter { it is Student } as List<Student>)
            .groupBy { it.house }
        val gryffindorStudents = houseStudents[gryffindor]
        val slytherinStudents = houseStudents[slytherin]
        val ravenclawStudents = houseStudents[ravenclaw]
        val hufflepuffStudents = houseStudents[hufflepuff]

        assertEquals(3, gryffindorStudents?.size)
        assertEquals(1, slytherinStudents?.size)
        assertEquals(0, ravenclawStudents?.size ?: 0)
        assertEquals(0, hufflepuffStudents?.size ?: 0)
    }

    @Test
    fun `is wand choosing wizard correct`() {
        assertEquals(null, harryPotter.wand)
        Wand.chooseWizard(harryPotter)
        assertEquals(Wand.Wood.HOLLY, harryPotter.wand?.wood)
        assertEquals(Wand.Core.PHOENIX_FEATHER, harryPotter.wand?.core)
        assertEquals(11.0, harryPotter.wand?.length)

        assertEquals(null, hermioneGranger.wand)
        val hermioneWand = Wand(Wand.Wood.VINE, Wand.Core.DRAGON_HEARTSTRING, 13.4)
        hermioneWand.chooseWizard(hermioneGranger)
        assertEquals(hermioneWand, hermioneGranger.wand)
    }

    @Test
    fun `is Albus Dumbledore headmaster`() {
        assertEquals(Professor.Position.HEADMASTER.toString().toLowerCase().capitalize(), albusDumbledore.position)
    }

    @Test
    fun `is Hagrid strong`() {
        assertEquals(5.0, hagrid.power, 0.001)
    }

    @Test
    fun `is student age average correct`() {
        harryPotter.dateOfBirth = "31/07/1980"
        hermioneGranger.dateOfBirth = "19/09/1979"
        ronWeasley.dateOfBirth = "01/03/1980"
        dracoMalfoy.dateOfBirth = "05/06/1980"

        val averageAge: Int = listOf(harryPotter, hermioneGranger, ronWeasley, dracoMalfoy)
            .map { it.age }
            .average()
            .roundToInt()

        // yeah, these are old students
        assertEquals(38, averageAge)
    }

    @Test
    fun `is potion course duration correct`() {
        potionCourse.duration = 90
        val (name, duration) = potionCourse
        assertEquals("Potion", name)
        assertEquals(90, duration)
    }
}