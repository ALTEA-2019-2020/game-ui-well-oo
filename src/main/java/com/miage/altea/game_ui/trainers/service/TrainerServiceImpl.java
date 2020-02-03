package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String url;

    @Override
    public List<Trainer> getAllTrainers() {
        return List.of(this.restTemplate.getForObject(this.url + "/trainers/", Trainer[].class));
    }

    @Override
    public Trainer getTrainer(String name) {
        return null;
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${trainer.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.url = pokemonServiceUrl;
    }

}
