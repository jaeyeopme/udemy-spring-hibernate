package com.spring.crm.rest.service;

import com.spring.crm.rest.entity.Customer;
import com.spring.crm.rest.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    @Override
    public List<Customer> getCustomers() {
        return customerRepository.getCustomers();
    }

    @Transactional
    @Override
    public Customer getCustomer(Long customerId) {
        return customerRepository.getCustomer(customerId);
    }

    @Transactional
    @Override
    public void saveCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    @Transactional
    @Override
    public void deleteCustomer(Long customerId) {
        customerRepository.deleteCustomer(customerId);
    }
}
