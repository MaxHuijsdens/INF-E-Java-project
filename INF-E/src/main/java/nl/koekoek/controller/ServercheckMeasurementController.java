package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.controller.model.ServercheckMeasurementJsonModel;
import nl.koekoek.model.ServercheckMeasurement;
import nl.koekoek.service.MeasurementService;
import nl.koekoek.service.ServerService;
import nl.koekoek.support.RestResult;

import org.jarbframework.constraint.violation.UniqueKeyViolationException;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for ServerMeasurement service.
 * @author Tom
 * 
 */
@Controller
@RequestMapping(value = "/servermeasurement")
public class ServercheckMeasurementController {

    private final MeasurementService measurementService;

    private final ServerService serverService;

    /**
     * The constructor. 
     * @param measurementService the measurementService
     * @param serverService the serverService
     */
    @Autowired
    public ServercheckMeasurementController(MeasurementService measurementService, ServerService serverService) {
        this.measurementService = measurementService;
        this.serverService = serverService;
    }

    /**
     * Retrieve data from a certain metric of a server.
     * @param id The id  of the server you want to retrieve
     * @param graphType The data field you want to retrieve (example: LOAD, CPU)
     * @param startDate The start date
     * @param endDate The end date
     * @return List of array objects in json format
     */
    @RequestMapping(value = "/server/{id}/graph/{graphType}/start/{startDate}/end/{endDate}/", method = RequestMethod.GET)
    @ResponseBody
    public List<Number[]> getGraphicalData(@PathVariable long id, @PathVariable String graphType,
            @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        return this.measurementService.getGraphicalDataByServerId(id, graphType, startDate, endDate, "SE");
    }

    /**
     * Creates a serverMeasurement.
     * @param serverMeasurementProcessor represents the json
     * @return the RestResult of the serverMeasurement
     */
    @SuppressWarnings("unchecked")
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public RestResult<ServercheckMeasurement> create(@RequestBody ServercheckMeasurementJsonModel serverMeasurementProcessor) {
        ServercheckMeasurement newServerMeasurement = serverMeasurementProcessor.createServerMeasurement(serverService, measurementService);
        return createOrEdit(newServerMeasurement);
    }

    private RestResult<ServercheckMeasurement> createOrEdit(ServercheckMeasurement serverMeasurement) {
        try {
            return RestResult.success(measurementService.saveServercheckMeasurement(serverMeasurement));
        } catch (UniqueKeyViolationException e) {
            return RestResult.error("Error occured while creating serverMeasurement");
        }
    }

}
