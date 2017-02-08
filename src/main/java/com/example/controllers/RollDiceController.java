package com.example.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * Created by RyanHarper on 2/8/17.
 */
@Controller
public class RollDiceController {

    @GetMapping("/roll-dice")
    public String rollDice() {
        return "roll-dice";
    }

    @GetMapping("/roll-dice/{guess}")
    public String rollDiceCompare(@PathVariable int guess, Model model) {

        int random = (int)(Math.random() * 6 + 1);
        model.addAttribute("random", random);
        model.addAttribute("guess", guess);

        return "roll-dice";
    }
}
