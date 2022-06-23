package org.lambda.hateoas.person.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.lambda.hateoas.core.model.domain.Person
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository: PanacheRepository<Person>
