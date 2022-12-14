package org.lambda.bacasable.simpleapi.person.model.mapper

import org.lambda.bacasable.simpleapi.core.config.mapper.CustomMapperConfig
import org.lambda.bacasable.simpleapi.core.model.domain.Person
import org.lambda.bacasable.simpleapi.person.model.dto.PersonDTO
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.MappingTarget

@Mapper(config = CustomMapperConfig::class)
interface PersonMapper{
    @Mapping(target = "restServiceDiscovery", ignore = true)
    fun toPersonDto(person: Person): PersonDTO

    @Mapping(target = "id", ignore = true)
    fun toPerson(personDTO: PersonDTO): Person

    fun toExistingPerson(personDTO: PersonDTO, @MappingTarget person: Person): Person
}
