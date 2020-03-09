package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.bo.TrainerPokemonTypeWithLevel;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class ProfileController {


    private TrainerService trainerService;

    @GetMapping("/profile")
    public ModelAndView profile(Principal principal){
        ModelAndView modelAndView = new ModelAndView("trainer");
        TrainerPokemonTypeWithLevel trainer = this.trainerService.getTrainer(principal.getName());
        modelAndView.addObject("trainer",trainer);
        return  modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
