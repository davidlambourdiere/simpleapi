package org.lambda.hateoas.person.resource

import org.jboss.resteasy.links.AddLinks
import org.jboss.resteasy.links.LinkResource
import org.lambda.hateoas.person.model.dto.PersonDTO
import org.lambda.hateoas.person.service.PersonService
import javax.ws.rs.*
import javax.ws.rs.core.MediaType

@Path("/person")
class PersonResource(val personService: PersonService) {
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @LinkResource
    @AddLinks
    fun getPerson(@PathParam("id") id: Long): PersonDTO = personService.getPerson(id)

    @GET
    @Produces(MediaType.APPLICATION_JSON, "application/hal+json")
    @AddLinks
    fun getAllPerson(): List<PersonDTO> = personService.getAllPerson()

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AddLinks
    @LinkResource
    fun createPerson(personDto: PersonDTO) = personService.createPerson(personDto)

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AddLinks
    @LinkResource
    fun updatePerson(@PathParam("id") id: Long, personDto: PersonDTO) = personService.updatePerson(id, personDto)

}
