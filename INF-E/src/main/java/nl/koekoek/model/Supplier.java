package nl.koekoek.model;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Customer Object.
 * @author Kevin van Leeuwen
 *
 */
@SuppressWarnings("serial")
@Entity
@JsonIgnoreProperties({ "new" })
public class Supplier extends AbstractEntity {

    private String name;

    /**
     * default constructor.
     */
    public Supplier() {
        super();
    }

    /**
     * Custom contructor.
     * @param name of the supplier
     */
    public Supplier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
