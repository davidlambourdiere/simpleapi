package org.lambda.hateoas.core.model.domain

import javax.persistence.*

@Entity
@Table(name= "person")
class Person(@Id @GeneratedValue
             val id: Long? = null,
             @Column(nullable = false)
             var firstname: String,
             @Column(nullable = false)
             var lastname:String,
             @Column(nullable = false)
             var age: Int)
