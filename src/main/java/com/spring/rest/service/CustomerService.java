package com.spring.rest.service;

import com.spring.rest.entity.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> getCustomers();

    Customer getCustomer(Long customerId);

    void saveCustomer(Customer customer);

    void deleteCustomer(Long customerId);

}
