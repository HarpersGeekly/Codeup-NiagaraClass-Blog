package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by RyanHarper on 2/7/17.
 */
//first step after creating the class is to import Controller from the framework:
@Controller
public class MathController {

    //We want to get a request from the user, import GetMapping from framework:
    @GetMapping("/add/{x}/and/{y}")
    //we want the input to be displayed, import ResponseBody from framework:
    @ResponseBody
    // you need a path variable to accept the variables in the path:
    public String addition(@PathVariable int x, @PathVariable int y) {
        return "<h1> Addition: " + x + " + " + y + " = " + (x + y) + "</h1>";
    }

    @GetMapping("/subtract/{x}/from/{y}")
    @ResponseBody
    public String subtraction(@PathVariable int x, @PathVariable int y) {
        return "<h1> Subtraction: " + x + " - " + y + " = " + (x - y) + "</h1>";

    }
    @GetMapping("/multiply/{x}/by/{y}")
    @ResponseBody
    public String multiply(@PathVariable int x, @PathVariable int y) {
        return "<h1> Multiplication: " + x + " * " + y + " = " + (x * y) + "</h1>";
    }

    @GetMapping("/divide/{x}/by/{y}")
    @ResponseBody
    public String divide(@PathVariable int x, @PathVariable int y) {
        return "<h1> Division: " + x + " / " + y + " = " + (x / y) + "</h1>";
    }
}
