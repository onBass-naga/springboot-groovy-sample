package com.example.service

import com.example.domain.Customer
import com.example.domain.User
import com.example.repository.CustomerRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
@Transactional
class CustomerService {
    @Autowired
    CustomerRepository customerRepository

    Customer save(Customer customer, User user) {
        customer.user = user
        return customerRepository.save(customer)
    }

    Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable)
    }

    Customer findOne(Integer id) {
        return customerRepository.findOne(id)
    }

    Page<Customer> findAllOrderByName(Pageable pageable) {
        return customerRepository.findAllOrderByName(pageable)
    }

    List<Customer> findAllOrderByNameDesc() {
        return customerRepository.findAllOrderByNameDesc()
    }

    void delete(Integer id) {
        customerRepository.delete(id)
    }
}
