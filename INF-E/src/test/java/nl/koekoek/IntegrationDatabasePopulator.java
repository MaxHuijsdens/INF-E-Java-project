/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek;

import nl.koekoek.builder.CustomerBuilder;
import nl.koekoek.builder.ServerBuilder;
import nl.koekoek.builder.SlaBuilder;
import nl.koekoek.model.Customer;
import nl.koekoek.model.Sla;

import org.jarbframework.populator.DatabasePopulator;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 *
 * @author jeroen
 * @since Aug 22, 2014
 */
public class IntegrationDatabasePopulator implements DatabasePopulator {
    
    @Autowired
    private CustomerBuilder customerBuilder;
    
    @Autowired
    private SlaBuilder slaBuilder;
    
    @Autowired
    private ServerBuilder serverBuilder;

    @Override
    public void populate() {
        Customer customer = customerBuilder.create("My customer");
        Sla sla = slaBuilder.newSla().withName("My first sla").withNumber("1").withCustomer(customer).create();
        serverBuilder.newServer().withHostName("My first server").withSla(sla).create();
    }
    
}
