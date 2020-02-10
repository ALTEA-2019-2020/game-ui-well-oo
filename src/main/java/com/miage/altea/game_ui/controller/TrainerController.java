package com.miage.altea.game_ui.controller;

import com.miage.altea.game_ui.bo.TrainerPokemonTypeWithLevel;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import com.miage.altea.game_ui.trainers.service.TrainerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TrainerController {


    private TrainerService trainerService;

    @GetMapping("/trainers")
    public ModelAndView trainers(){
        ModelAndView modelAndView = new ModelAndView("trainers");
        modelAndView.addObject("trainers",this.trainerService.getAllTrainers());
        return  modelAndView;
    }

    @GetMapping("/trainers/{name}")
    public ModelAndView trainer(@PathVariable String name){
        ModelAndView modelAndView = new ModelAndView("trainer");
        TrainerPokemonTypeWithLevel trainer = this.trainerService.getTrainer(name);
        modelAndView.addObject("trainer",trainer);
        return  modelAndView;
    }

    @Autowired
    public void setTrainerService(TrainerService trainerService) {
        this.trainerService = trainerService;
    }
}
