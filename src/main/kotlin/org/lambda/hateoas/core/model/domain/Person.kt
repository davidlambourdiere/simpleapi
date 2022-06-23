package org.lambda.hateoas.core.model.domain

import javax.persistence.*

@Entity
@Table(name= "person")
class Person(@Id @GeneratedValue
             val id: Long? = null,
             @Column(nullable = false)
             val firstname: String,
             @Column(nullable = false)
             val lastname:String,
             @Column(nullable = false)
             val age: Int)
