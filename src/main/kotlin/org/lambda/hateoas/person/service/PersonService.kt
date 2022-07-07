package org.lambda.hateoas.person.service

import org.lambda.hateoas.person.model.dto.PersonDTO
import org.lambda.hateoas.person.model.mapper.PersonMapper
import org.lambda.hateoas.person.repository.PersonRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.NotFoundException

@ApplicationScoped
class PersonService(
        val personRepository: PersonRepository,
        val personMapper: PersonMapper) {


    fun getPerson(id: Long): PersonDTO = personMapper.toPersonDto(findPersonById(id))

    @Transactional
    fun createPerson(personDto: PersonDTO): PersonDTO {
        return personMapper.toPerson(personDto).let {
            personRepository.persist(it)
            personMapper.toPersonDto(it)
        }
    }
    fun getAllPerson(): List<PersonDTO> = personRepository.listAll().map { personMapper.toPersonDto(it) }

    @Transactional
    fun updatePerson(id: Long, personDto: PersonDTO): PersonDTO {
        val person = findPersonById(id)
        return personMapper.toExistingPerson(personDto, person).let {
            personRepository.persist(it)
            personMapper.toPersonDto(it)
        }
    }

    fun findPersonById(id: Long) =
            personRepository.findById(id) ?: throw NotFoundException("Couldn't find person with id %d".format(id))
}
