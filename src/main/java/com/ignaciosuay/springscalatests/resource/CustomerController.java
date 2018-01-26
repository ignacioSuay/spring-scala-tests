package com.ignaciosuay.springscalatests.resource;

import com.ignaciosuay.springscalatests.model.Customer;
import com.ignaciosuay.springscalatests.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/customers/{id}")
    public Customer getCustomer(@PathVariable Long id){
        log.info("Get customer by id {}", id);
        return customerService.findCustomer(id);
    }
}
