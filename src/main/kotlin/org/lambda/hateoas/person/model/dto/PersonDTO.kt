package org.lambda.hateoas.person.model.dto

import org.lambda.hateoas.core.annotation.NoArgConstructor

@NoArgConstructor
class PersonDTO(val id: Long?,
                val firstname: String?,
                val lastname: String?,
                val age: Int?)
