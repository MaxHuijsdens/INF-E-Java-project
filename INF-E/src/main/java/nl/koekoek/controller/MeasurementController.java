package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.model.Measurement;
import nl.koekoek.service.MeasurementService;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller class for measurement.
 * @author Bastiaan Klein
 * 
 */
@Controller
@RequestMapping(value = "/measurement")
public class MeasurementController {

    private final MeasurementService measurementService;

    /**
     * Constructor for MeasurementController object.
     * @param measurementService object
     */
    @Autowired
    public MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    /**
     * Returns latest measurements for every server..
     * @return latest measurements for every server
     */
    @RequestMapping(value = "/getLatest", method = RequestMethod.GET)
    @ResponseBody
    public List<Measurement> getLatestMeasurementPerServers() {
        return measurementService.getLatestMeasurementsPerServer(null);
    }

    /**
     * Return latest measurements for every server that belongs with this SLA.
     * @param slaId sla the server belongs to
     * @return latest measurements for every server
     */
    @RequestMapping(value = "/getLatest/slaId/{slaId}", method = RequestMethod.GET)
    @ResponseBody
    public List<Measurement> getLatestMeasurementPerServersBySla(@PathVariable Long slaId) {
        return measurementService.getLatestMeasurementsPerServer(slaId);
    }

    /**
     * Returns measurementdata for a server for a specific metric/graphtype, source and period.
     * @param id from the server
     * @param graphType type of graphical data
     * @param startDate start date
     * @param endDate end date
     * @param source of the measurement
     * @return Returns measurementdata for a server for a specific source nd specific period
     */
    @RequestMapping(value = "/server/{id}/graph/{graphType}/start/{startDate}/end/{endDate}/source/{source}", method = RequestMethod.GET)
    @ResponseBody
    public List<Number[]> getGraphicalData(@PathVariable long id, @PathVariable String graphType,
            @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate, @PathVariable String source) {
        return this.measurementService.getGraphicalDataByServerId(id, graphType, startDate, endDate, source);
    }

}
