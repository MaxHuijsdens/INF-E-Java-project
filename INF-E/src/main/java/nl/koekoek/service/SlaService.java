package nl.koekoek.service;

import java.util.List;

import nl.koekoek.model.Sla;
import nl.koekoek.repository.SlaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * SLA service, talks to the repository.
 * @author bastiaan
 *
 */
@Service
@Transactional
public class SlaService {

    private SlaRepository slaRepository;

    /**
     * Default contructor.
     */
    public SlaService() {

    }

    /**
     * Contructor where tje sla repository is autowired.
     * @param slaRepository the sla repository
     */
    @Autowired
    public SlaService(SlaRepository slaRepository) {
        this.slaRepository = slaRepository;
    }

    /**
     * Adds a SLA to the repository. 
     * @param sla to be saved
     * @return SLA that is saved
     */
    public Sla addSla(Sla sla) {
        return this.slaRepository.save(sla);
    }

    /**
     * Delete a SLA from the repository. 
     * @param sla to be deleted
     */
    public void deleteSla(Sla sla) {
        this.slaRepository.delete(sla);
    }

    /**
     * Finds a sla by its id.
     * @param id of the sla
     * @return a sla
     */
    public Sla findById(long id) {
        return this.slaRepository.findOne(id);
    }

    /**
     * Get all SLA's.
     * @return all the SLA´s in the database
     */
    public List<Sla> getAllSlas() {
        return this.slaRepository.findAll();
    }

    /**
     * Get all sla for a specific customer.
     * @param customerId is for the customer    
     * @return  list off SLA's
     */
    public List<Sla> getAllSlasByCustomer(long customerId) {
        return this.slaRepository.findByCustomerId(customerId);
    }
}
