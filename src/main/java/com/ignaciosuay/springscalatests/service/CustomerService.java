package com.ignaciosuay.springscalatests.service;

import com.ignaciosuay.springscalatests.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public Customer findCustomer(Long id) {
        return new Customer(id, "Bob");
    }
}
