package org.lambda.bacasable.simpleapi.person.model.dto

import org.jboss.resteasy.links.ResourceID
import org.lambda.bacasable.simpleapi.core.annotation.NoArgConstructor
import org.lambda.bacasable.simpleapi.core.model.dto.HateoasDto

@NoArgConstructor
class PersonDTO(@ResourceID val id: Long?,
                val firstname: String?,
                val lastname: String?,
                val age: Int?) : HateoasDto()
