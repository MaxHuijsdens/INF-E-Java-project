/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.builder;

import nl.koekoek.model.Customer;
import nl.koekoek.service.CustomerService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerBuilder {
    
    @Autowired
    private CustomerService customerService;

    public Customer create(String name) {
        Customer customer = new Customer();
        customer.setName(name);
        return customerService.addCustomer(customer);
    }
    
}
