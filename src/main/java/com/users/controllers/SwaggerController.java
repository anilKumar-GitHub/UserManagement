package com.users.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Swagger documentation end point directly available at root path application.
 * URL : localhost:8080//
 * 
 * @author anilKumar
 *
 */
@Controller
public class SwaggerController {

    @GetMapping
    public String swaggerUI() {
        
    	return "redirect:/swagger-ui.html";
    }
}
