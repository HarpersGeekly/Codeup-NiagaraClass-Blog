package com.example.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanHarper on 2/7/17.
 */
@Controller
public class HelloWorldController {

    @GetMapping("/home")
    public String homePage() {
        return "HomePage"; // [/HomePage.html] from the filename.
    }

    @GetMapping("/contact")
    public String contactPage() {
        return "contact/ContactForm";
    }

    @GetMapping("/hello/{name}")
    @ResponseBody
    public String hello(@PathVariable String name) {
        return "<h1>Hello " + name + " from Spring!</h1>";
    }

    // RequestMapping is the same as GetMapping, but longer...eh to use
    @RequestMapping(path = "/bye/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String bye(@PathVariable String name) {
        return "<h1>Goodbye " + name + "! from Spring</h1>";
    }

        // demo from class
    @GetMapping("/demo")
    public String showDefault(Model model) {

        List<String> names = new ArrayList<>();

        names.add("Ryan");
        names.add("Grant");
        names.add("Lauren");
        names.add("Lynne");
        model.addAttribute("date", "Feb 7th");
        model.addAttribute("age", 31);
        model.addAttribute("names", names);
        return "default";
    }

    @GetMapping("/resume")
    public String resumePage() {
        return "resume";
    }

    @GetMapping("/portfolio")
    public String portfolioPage() {
        return "portfolio";
    }

}
