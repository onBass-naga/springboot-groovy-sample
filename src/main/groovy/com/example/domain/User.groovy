package com.example.domain

import com.fasterxml.jackson.annotation.JsonIgnore
import groovy.transform.Canonical
import groovy.transform.ToString

import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.persistence.Table

@Canonical
@ToString(excludes="customers")
@Entity
@Table(name = "users")
class User {
    @Id
    String username

    @JsonIgnore
    String encodedPassword

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    List<Customer> customers
}
