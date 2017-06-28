package nl.koekoek.controller;

import java.util.List;

import nl.koekoek.service.MeasurementService;

import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Controller for n2measurement calls.
 * @author Tom.
 *
 */
@Controller
@RequestMapping(value = "/n2")
public class N2MeasurementController {

    private final MeasurementService measurementService;

    /**
     * Default constructor.
     * @param measurementService Service used for the calls
     */
    @Autowired
    public N2MeasurementController(MeasurementService measurementService) {
        this.measurementService = measurementService;
    }

    /**
     * Return graphical data from a certain metric of a server.
     * @param id The id  from the server you want to return data from
     * @param graphType The metric you want to return(example LOAD, CPU)
     * @param startDate The start date format(yyyy-mm-ddThh:mm)
     * @param endDate The end date format(yyyy-mm-ddThh:mm)
     * @return List of array object[date, value]
     */
    @RequestMapping(value = "/server/{id}/graph/{graphType}/start/{startDate}/end/{endDate}/", method = RequestMethod.GET)
    @ResponseBody
    public List<Number[]> getGraphicalDataByServerId(@PathVariable long id, @PathVariable String graphType,
            @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
        return measurementService.getGraphicalDataByServerId(id, graphType, startDate, endDate, "n2");
    }

}
