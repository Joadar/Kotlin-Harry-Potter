package io.smallant.wizard

import io.smallant.wizard.characters.*
import io.smallant.wizard.houses.*
import io.smallant.wizard.school.Course
import io.smallant.wizard.school.SortingHat
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class CharactersUnitTest {

    private val potionCourse = Course("Potion")

    private val harryPotter = Student("Harry", "Potter", Sexe.MALE)
    private val hermioneGranger = Student("Hermione", "Granger", Sexe.FEMALE)
    private val ronWeasley = Student("Ron", "Weasley", Sexe.MALE)
    private val dracoMalfoy = Student("Draco", "Malfoy", Sexe.MALE)

    private val severusSnape = Professor("Severus", "Snape", Sexe.MALE, Slytherin(), potionCourse)

    private val randomWizard = Wizard("Jeanne", "Doe", Sexe.FEMALE)

    private val gryffindor: Gryffindor = Gryffindor()
    private val ravenclaw: Ravenclaw = Ravenclaw()
    private val hufflepuff: Hufflepuff = Hufflepuff()
    private val slytherin: Slytherin = Slytherin()

    private lateinit var listOfWizards: List<Wizard>

    @Before
    fun setUp() {
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
        assertTrue(harryPotter.house is HowgwartHouse)
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
        val students: List<Student> = listOfWizards.filter { it is Student } as List<Student>
        val houseStudents = students.groupBy { it.house }
        val gryffindorStudents = houseStudents[gryffindor]
        val slytherinStudents = houseStudents[slytherin]
        val ravenclawStudents = houseStudents[ravenclaw]
        val hufflepuffStudents = houseStudents[hufflepuff]

        assertEquals(3, gryffindorStudents?.size)
        assertEquals(1, slytherinStudents?.size)
        assertEquals(0, ravenclawStudents?.size ?: 0)
        assertEquals(0, hufflepuffStudents?.size ?: 0)
    }
}