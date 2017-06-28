package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.model.Supplier;
import nl.koekoek.service.SupplierService;
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
 * Controller class for supplier.
 * @author Kevin van Leeuwen
 * 
 */
@Controller
@RequestMapping(value = "/supplier")
public class SupplierController {

    private final SupplierService supplierService;

    /**
     * Constructor for SupplierController object.
     * @param supplierService SupplierService object to use in the controller
     */
    @Autowired
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    /**
     * Returns all suppliers.
     * @return List with all suppliers
     */
    @RequestMapping(value = "/supplierList", method = RequestMethod.GET)
    @ResponseBody
    public List<Supplier> getSupplierList() {
        return supplierService.getAllSuppliers();
    }

    /**
     * Creates a supplier.
     * @param supplier new supplier object
     * @return the Restresult for a supplier
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Supplier> create(@RequestBody Supplier supplier) {
        try {
            return RestResult.success(supplierService.saveSupplier(supplier));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("Leverancier " + supplier.getName() + " bestaat al.");
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Leverancier " + supplier.getName() + " bestaat al.");
        }
    }

    /**
     * Updates a supplier.
     * @param newSupplier The supplier to be updated
     * @param supplier Supplier pathvariable
     * @return the Restresult for a supplier
     */
    @RequestMapping(value = "/{supplier}", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Supplier> edit(@RequestBody Supplier newSupplier, @PathVariable("supplier") Supplier supplier) {
        return RestResult.success(supplierService.saveSupplier(newSupplier));
    }

    /**
     * Deletes a supplier.
     * @param supplierId id of the supplier to be deleted
     * @return restresult 
     */
    @RequestMapping(value = "/delete/{supplierId}", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult<Supplier> delete(@PathVariable long supplierId) {
        try {
            Supplier supplier = supplierService.findById(supplierId);
            supplierService.deleteSupplier(supplier);
            return RestResult.success(supplier);
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("Leverancier nog gekoppeld aan een server.");
        }
    }
}
