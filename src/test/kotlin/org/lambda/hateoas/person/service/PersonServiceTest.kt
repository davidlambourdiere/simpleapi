package org.lambda.hateoas.person.service

import io.mockk.*
import io.mockk.junit5.MockKExtension
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.lambda.hateoas.core.model.domain.Person
import org.lambda.hateoas.person.model.dto.PersonDTO
import org.lambda.hateoas.person.model.mapper.PersonMapper
import org.lambda.hateoas.person.repository.PersonRepository
import java.time.LocalDateTime
import javax.ws.rs.NotFoundException

@ExtendWith(MockKExtension::class)
internal class PersonServiceTest {

    lateinit var mockedPerson1: Person

    lateinit var mockedPersonDto1: PersonDTO

    lateinit var personMapper: PersonMapper

    lateinit var personRepository: PersonRepository

    lateinit var personService: PersonService

    @BeforeEach
    fun setUp(){
        // DOC
        personRepository = mockk()
        personMapper = mockk()

        //SUT
        personService = PersonService(personRepository = personRepository, personMapper = personMapper)

        mockedPerson1 = Person(
                id = 1,
                firstname = "Jon",
                lastname = "Snow",
                age = 21,
                createdAt = LocalDateTime.of(2022,9,2, 0, 0 , 0),
                updatedAt = LocalDateTime.of(2022,9,2, 0, 0 , 0))

        mockedPersonDto1 = PersonDTO(
                id = 1,
                firstname = "Jon",
                lastname = "Snow",
                age = 21)
        every { personMapper.toPersonDto(mockedPerson1) } returns mockedPersonDto1
        every { personMapper.toPerson(mockedPersonDto1) } returns mockedPerson1
    }

    @Test
    fun findPersonById_shouldSuccess_whenGoodId() {
        //given
        every { personRepository.findById(any()) } returns mockedPerson1
        //when
        val personById = personService.findPersonById(1)
        //then
        assertEquals(mockedPerson1, personById)
    }

    @Test
    fun findPersonById_shouldFail_whenWrongId() {
        //given
        every { personRepository.findById(any()) } returns null
        //when then
        assertThrows<NotFoundException> { personService.findPersonById(1) }
    }

    @Test
    fun getPerson_shouldReturnExpectedPersonDto_whenGoodId() {
        //given
        every { personService.findPersonById(any()) } returns mockedPerson1

        //when
        val personDto = personService.getPerson(1)

        //then
        verify { personService.findPersonById(1) }
        verify { personMapper.toPersonDto(mockedPerson1) }
        assertEquals(mockedPersonDto1, personDto);
    }

    @Test
    fun getAllPerson_shouldReturnListOfPersonDto(){
        //given
        every { personRepository.listAll() } returns listOf(mockedPerson1)

        //when
        val persons = personService.getAllPerson()

        //then
        verify { personRepository.listAll() }
        verify {  personMapper.toPersonDto(mockedPerson1) }
        assertEquals(listOf(mockedPersonDto1), persons)
    }

    @Test
    fun createPerson_shouldPersistPerson_whenGivenPersonDto(){
        //given
        every { personRepository.persist(mockedPerson1) } just Runs
        //when
        personService.createPerson(mockedPersonDto1)
        //then
        verify { personMapper.toPerson(mockedPersonDto1) }
        verify { personRepository.persist(mockedPerson1) }
    }

    @Test
    fun updatePerson_shouldPersistPerson_whenGivenPersonDto(){
        //given
        every { personService.findPersonById(any()) } returns mockedPerson1
        every { personRepository.persist(mockedPerson1) } just Runs
        every { personMapper.toExistingPerson(mockedPersonDto1, mockedPerson1) } returns mockedPerson1

        //when
        personService.updatePerson(1, mockedPersonDto1)

        //then
        verify { personService.findPersonById(1) }
        verify { personMapper.toExistingPerson(mockedPersonDto1, mockedPerson1) }
        verify { personRepository.persist(mockedPerson1) }
    }
}
