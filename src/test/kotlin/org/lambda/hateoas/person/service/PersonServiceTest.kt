package org.lambda.hateoas.person.service

import io.mockk.every
import io.mockk.junit5.MockKExtension
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.lambda.hateoas.core.model.domain.Person
import org.lambda.hateoas.person.model.mapper.PersonMapper
import org.lambda.hateoas.person.repository.PersonRepository
import java.time.LocalDateTime
import javax.ws.rs.NotFoundException

@ExtendWith(MockKExtension::class)
internal class PersonServiceTest {

    lateinit var mockedPerson1: Person

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
}
