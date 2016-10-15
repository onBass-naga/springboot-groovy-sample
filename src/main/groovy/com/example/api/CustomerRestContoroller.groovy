package com.example.api

import com.example.domain.Customer
import com.example.service.CustomerService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("api/customers")
class CustomerRestContoroller {

    @Autowired
    CustomerService customerService

    @GetMapping
    Page<Customer> getCustomers(@PageableDefault Pageable pageable) {
        return customerService.findAll(pageable)
    }

    @GetMapping(path = "{id}")
    Customer getCustomer(@PathVariable Integer id) {
        return customerService.findOne(id)
    }

    @PostMapping
    ResponseEntity<Customer> post(@RequestBody Customer customer,
                                  UriComponentsBuilder uriBuilder) {
        def created = customerService.save(customer)
        URI location = uriBuilder.path("api/customers/{id}")
                .buildAndExpand(created.id)
                .toUri()

        return ResponseEntity.created(location).body(created)
    }

    @PutMapping(path = "{id}")
    Customer put(@PathVariable Integer id, @RequestBody Customer customer) {
        customer.id = id
        return customerService.save(customer)
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        customerService.delete(id)
    }
}
