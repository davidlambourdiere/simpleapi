package org.lambda.hateoas.person.model.dto

import org.jboss.resteasy.links.ResourceID
import org.lambda.hateoas.core.annotation.NoArgConstructor
import org.lambda.hateoas.core.model.dto.HateoasDto

@NoArgConstructor
class PersonDTO(@ResourceID val id: Long?,
                val firstname: String?,
                val lastname: String?,
                val age: Int?) : HateoasDto()
