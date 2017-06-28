/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.controller;

import nl.koekoek.service.ApplicationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 *
 * @author jeroen
 * @since Aug 25, 2014
 */
@Controller
@RequestMapping(value = "/application", method = RequestMethod.GET)
public class ApplicationController {

    private final ApplicationService applicationService;

    /**
     * Default Contructor.
     * @param applicationService the ApplicationService
     */
    @Autowired
    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public ApplicationService getCurrentVersion()
    {
	return this.applicationService;
    }
}
