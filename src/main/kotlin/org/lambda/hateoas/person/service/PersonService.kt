package org.lambda.hateoas.person.service

import org.lambda.hateoas.person.model.dto.PersonDTO

interface PersonService {
    fun getPerson(id: Long): PersonDTO

    fun createPerson(personDto: PersonDTO): PersonDTO

    fun getAllPerson(): List<PersonDTO>

    fun updatePerson(id: Long, personDto: PersonDTO): PersonDTO
}
