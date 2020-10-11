package com.spring.rest.repository;

import com.spring.rest.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getCustomers();

    Customer getCustomer(Long customerId);

    void saveCustomer(Customer customer);

    void deleteCustomer(Long customerId);

}
