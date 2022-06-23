package org.lambda.hateoas.person.model.mapper

import org.lambda.hateoas.core.config.mapper.CustomMapperConfig
import org.lambda.hateoas.core.model.domain.Person
import org.lambda.hateoas.person.model.dto.PersonDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(config = CustomMapperConfig::class)
interface PersonMapper{
    fun toPersonDto(person: Person): PersonDTO
    @Mapping(target = "id", ignore = true)
    fun toPerson(persopnDTO: PersonDTO): Person
    fun toExistingPerson(personDTO: PersonDTO, @MappingTarget person: Person)
}