package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Supplier;
import nl.koekoek.repository.SupplierRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * implementation of a SupplierService interface.
 * @author Kevin van Leeuwen
 * 
 */
@Service
@Transactional
public class SupplierService {

    private SupplierRepository supplierRepository;

    /**
     * Default contructor.
     */
    public SupplierService() {

    }

    /**
     * Constructor for a SupplierServiceImpl object.
     * @param supplierRepository The repository you want to use in this service class
     */
    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    /**
     * Retrieve all Supplier objects that are present in the database.
     * @return a List object with all supplier object
     */
    public List<Supplier> getAllSuppliers() {
        return supplierRepository.findAll();
    }

    /**
     * Store a Supplier object in the database.
     * @param supplier The Supplier object you want to store in the database
     * @return The stored Supplier
     */
    public Supplier saveSupplier(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    /**
     * Delete a Supplier object in the database.
     * @param supplier The supplier you want to delete.
     */
    public void deleteSupplier(Supplier supplier) {
        supplierRepository.delete(supplier);
    }

    /**
     * Finds a supplier by its id.
     * @param id from the supplier
     * @return the found supplier
     */
    public Supplier findById(long id) {
        return supplierRepository.findOne(id);
    }
}
