package nl.koekoek.model;

import javax.persistence.Entity;

/**
 * Customer Object.
 * @author Kevin van Leeuwen
 *
 */

@SuppressWarnings("serial")
@Entity
public class Customer extends AbstractEntity {

    private String name;

    /**
     * default constructor.
     */
    public Customer() {
        super();
    }

    /**
     * Contructor with name.
     * @param name of the customer
     */
    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
