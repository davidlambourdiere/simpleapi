package org.lambda.bacasable.simpleapi.person.repository

import io.quarkus.hibernate.orm.panache.PanacheRepository
import org.lambda.bacasable.simpleapi.core.model.domain.Person
import javax.enterprise.context.ApplicationScoped

@ApplicationScoped
class PersonRepository: PanacheRepository<Person>
