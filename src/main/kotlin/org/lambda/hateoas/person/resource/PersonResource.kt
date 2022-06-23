package org.lambda.hateoas.person.resource

import org.lambda.hateoas.person.model.dto.PersonDTO
import org.lambda.hateoas.person.service.PersonService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/person")
class PersonResource(val personService: PersonService) {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    fun getPerson(@PathParam("id") id: Long): PersonDTO = personService.getPerson(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    fun getAllPerson(): List<PersonDTO> = personService.getAllPerson()

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    fun createPerson(personDto: PersonDTO) = personService.createPerson(personDto)
}
