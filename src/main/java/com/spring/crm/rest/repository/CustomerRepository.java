package com.spring.crm.rest.repository;

import com.spring.crm.rest.entity.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> getCustomers();

    Customer getCustomer(Long customerId);

    void saveCustomer(Customer customer);

    void deleteCustomer(Long customerId);

}
