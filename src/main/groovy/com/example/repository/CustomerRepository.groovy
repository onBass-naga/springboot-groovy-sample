package com.example.repository

import com.example.domain.Customer
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository


@Repository
interface CustomerRepository extends JpaRepository<Customer, Integer> {

    @Query('SELECT x FROM Customer x ORDER BY x.firstName, x.lastName')
    Page<Customer> findAllOrderByName(Pageable pageable)

    @Query(value = 'SELECT x.id, x.first_name, x.last_name FROM customers x ORDER BY x.first_name desc, x.last_name desc', nativeQuery = true)
    List<Customer> findAllOrderByNameDesc()
}
