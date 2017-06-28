package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.model.Customer;
import nl.koekoek.service.CustomerService;
import nl.koekoek.service.SlaService;
import nl.koekoek.support.RestResult;

import org.jarbframework.constraint.violation.UniqueKeyViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for customer.
 * @author Kevin van Leeuwen
 * 
 */
@Controller
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;

    /**
     * Constructor for CustomerController object.
     * @param customerService CustomerService object to use in the controller
     */
    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    /**
     * Returns all customers.
     * @return List with all customers
     */
    @RequestMapping(value = "/customerList", method = RequestMethod.GET)
    @ResponseBody
    public List<Customer> getCustomerList() {
        return customerService.getAllCustomers();
    }

    /**
     * Creates a customer.
     * @param customer new customer object
     * @return RestResult of the customer
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Customer> create(@RequestBody Customer customer) {
        try {
            return RestResult.success(customerService.updateCustomer(customer));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("Klant " + customer.getName() + " bestaat al.");
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Klant " + customer.getName() + " bestaat al.");
        }
    }

    /**
     * Updates a customer.
     * @param customer the customer to be updated
     * @return Restresult of the customer
     */
    @RequestMapping(value = "/{customer}", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Customer> edit(@RequestBody Customer editCustomer, @PathVariable Customer customer) {
        return RestResult.success(customerService.addCustomer(editCustomer));
    }

    /**
     * Deletes a customer.
     * @param customerId is of the customer to be deleted
     * @return Restresult of the customer
     */
    @RequestMapping(value = "/delete/{customerId}", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult<Customer> deleteCustomer(@PathVariable long customerId) {
        try {
            Customer customer = customerService.findById(customerId);
            customerService.deleteCustomer(customer);
            return RestResult.success(customer);
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Customer nog gekoppeld aan een SLA.");
        }
    }
}
