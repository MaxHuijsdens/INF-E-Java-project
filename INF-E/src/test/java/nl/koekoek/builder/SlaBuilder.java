/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.builder;

import nl.koekoek.model.Customer;
import nl.koekoek.model.Sla;
import nl.koekoek.service.SlaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SlaBuilder {
    
    @Autowired
    private SlaService slaService;
    
    public SlaBuildCommand newSla() {
        return new SlaBuildCommand();
    }

    public class SlaBuildCommand {

        private Sla sla = new Sla();
        
        public SlaBuildCommand withName(String name) {
            sla.setName(name);
            return this;
        }
        
        public SlaBuildCommand withNumber(String number) {
            sla.setNumber(number);
            return this;
        }
        
        public SlaBuildCommand withCustomer(Customer customer) {
            sla.setCustomer(customer);
            return this;
        }
        
        public Sla create() {
            slaService.addSla(sla);
            return sla;
        }

    }
    
}
