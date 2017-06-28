package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.model.Sla;
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
 * Controller for SLA service.
 * @author Bastiaan
 * 
 */
@Controller
@RequestMapping(value = "/sla")
public class SlaController {

    private final SlaService slaService;

    /**
     * Constructor for a SlaController.
     * @param slaService the SlaService
     */
    @Autowired
    public SlaController(SlaService slaService) {
        this.slaService = slaService;
    }

    /**
     * saves a sla in the database.
     * @param sla
     * @return restresult of the saved SLA
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Sla> create(@RequestBody Sla sla) {
        try {
            return RestResult.success(slaService.addSla(sla));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("SLA " + sla.getNumber() + " bestaat al.");
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("SLA " + sla.getNumber() + " bestaat al.");
        }
    }

    /**
     * gets all SLA's.
     * @return all SLA's
     */
    @RequestMapping(value = "/slaList", method = RequestMethod.GET)
    @ResponseBody
    public List<Sla> getSlaList() {
        return slaService.getAllSlas();
    }

    /**
     * Return all SLA's for a specific customer.
     * @param customerId you want the sla for
     * @return list with SLA's
     */
    @RequestMapping(value = "/slaListByCustomer/{customerId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Sla> getSlaList(@PathVariable long customerId) {
        return slaService.getAllSlasByCustomer(customerId);
    }

    /**
     * Updates a SLA.
     * @param sla the sla to be updated
     * @return Restresult of the sla
     */
    @RequestMapping(value = "/{sla}", method = RequestMethod.POST)
    @ResponseBody
    public RestResult<Sla> edit(@RequestBody Sla editSla, @PathVariable Sla sla) {
        return RestResult.success(slaService.addSla(editSla));
    }

    /**
     * Deletes a sla.
     * @param slaId id of the sla
     * @return restresult
     */
    @RequestMapping(value = "/delete/{slaId}", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult<Sla> deleteCustomer(@PathVariable long slaId) {
        try {
            Sla sla = slaService.findById(slaId);
            slaService.deleteSla(sla);
            return RestResult.success(sla);
        } catch (DataIntegrityViolationException d) {
            return RestResult.error("SLA nog gekoppeld aan een server.");
        }
    }
}
