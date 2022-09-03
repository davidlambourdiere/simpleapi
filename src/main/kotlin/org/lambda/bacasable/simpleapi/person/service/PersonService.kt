package org.lambda.bacasable.simpleapi.person.service

import org.lambda.bacasable.simpleapi.person.model.dto.PersonDTO
import org.lambda.bacasable.simpleapi.person.model.mapper.PersonMapper
import org.lambda.bacasable.simpleapi.person.repository.PersonRepository
import javax.enterprise.context.ApplicationScoped
import javax.transaction.Transactional
import javax.ws.rs.NotFoundException

@ApplicationScoped
class PersonService(
        private val personRepository: PersonRepository,
        private val personMapper: PersonMapper) {


    fun getPerson(id: Long): PersonDTO = personMapper.toPersonDto(findPersonById(id))

    fun getAllPerson(): List<PersonDTO> = personRepository.listAll().map { personMapper.toPersonDto(it) }

    @Transactional
    fun createPerson(personDto: PersonDTO): PersonDTO {
        return personMapper.toPerson(personDto).let {
            personRepository.persist(it)
            personMapper.toPersonDto(it)
        }
    }

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
