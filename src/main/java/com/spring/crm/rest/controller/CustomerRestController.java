package com.spring.crm.rest.controller;

import com.spring.crm.rest.entity.Customer;
import com.spring.crm.rest.exception.CustomerNotFoundException;
import com.spring.crm.rest.service.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api")
@RestController
public class CustomerRestController {

    private final CustomerService customerService;

    public CustomerRestController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("customers")
    public void saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @GetMapping("customers")
    public List<Customer> getCustomers() {
        return customerService.getCustomers();
    }

    @GetMapping("customers/{customerId}")
    public Customer getCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Student is not found - " + customerId);
        }

        return customer;
    }

    @PutMapping("customers")
    public void updateCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
    }

    @DeleteMapping("customers/{customerId}")
    public String deleteCustomer(@PathVariable Long customerId) {
        Customer customer = customerService.getCustomer(customerId);

        if (customer == null) {
            throw new CustomerNotFoundException("Customer id not found - " + customerId);
        }

        customerService.deleteCustomer(customerId);

        return "Deleted customer id - " + customerId;
    }

}
