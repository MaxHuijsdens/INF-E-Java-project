/*
 * (C) 2013 42 bv (www.42.nl). All rights reserved.
 */
package nl.koekoek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 
 *
 * @author jeroen
 * @since Aug 22, 2014
 */
@Controller
@RequestMapping("/")
public class IndexController {

    @RequestMapping(method = RequestMethod.GET)
    public String index() {
        return "index.html";
    }

}
