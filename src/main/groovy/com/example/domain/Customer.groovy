package com.example.domain

import groovy.transform.Canonical

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table

@Canonical
@Entity
@Table(name = "customers")
class Customer {

    @Id
    @GeneratedValue
    Integer id

    @Column(nullable = false)
    String firstName

    @Column(nullable = false)
    String lastName

    @ManyToOne
    @JoinColumn(nullable = true, name = "username")
    User user
}
