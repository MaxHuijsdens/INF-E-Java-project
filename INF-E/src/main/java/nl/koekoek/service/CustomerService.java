package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Customer;
import nl.koekoek.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * CustomerService.
 * @author Kevin van Leeuwen
 * 
 */
@Service
@Transactional
public class CustomerService {

    private CustomerRepository customerRepository;

    /**
     * Default contructor.
     */
    public CustomerService() {

    }

    /**
     * Constructor for a CustomerServiceImpl object.
     * @param customerRepository The repository you want to use in this service class
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    /**
     * Retrieve all Customer objects that are present in the database.
     * @return a List object with all customer object
     */
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    /**
     * Store a Customer object in the database.
     * @param customer The Customer object you want to store in the database
     * @return The stored Customer
     */
    public Customer addCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    /**
     * Update a Customer object in the database.
     * @param customer The Customer object you want to update
     * @return The updated Customer
     */
    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    /**
     * Delete a Customer object in the database.
     * @param customer The customer you want to delete.
     */
    public void deleteCustomer(Customer customer) {
        this.customerRepository.delete(customer);
    }

    /**
     * Finds a customer by its Id.
     * @param customerId to be found
     * @return customer
     */
    public Customer findById(long customerId) {
        return this.customerRepository.findOne(customerId);
    }
}