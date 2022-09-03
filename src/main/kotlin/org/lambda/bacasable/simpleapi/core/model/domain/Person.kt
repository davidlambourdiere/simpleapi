package org.lambda.bacasable.simpleapi.core.model.domain

import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name= "person")
class Person(
        @Id @GeneratedValue
        val id: Long? = null,
        @Column(nullable = false)
        var firstname: String,
        @Column(nullable = false)
        var lastname:String,
        @Column(nullable = false)
        var age: Int,
        @Column
        @CreationTimestamp
        val createdAt: LocalDateTime?,
        @Column
        @UpdateTimestamp
        val updatedAt: LocalDateTime?)
