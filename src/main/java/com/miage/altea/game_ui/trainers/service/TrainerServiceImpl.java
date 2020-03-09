package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.bo.PokemonTypeWithLevel;
import com.miage.altea.game_ui.bo.TrainerPokemonTypeWithLevel;
import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import com.miage.altea.game_ui.trainers.bo.Trainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainerServiceImpl implements TrainerService {

    private RestTemplate restTemplate;
    private String url;
    private PokemonTypeService pokemonTypeService;

    @Override
    public List<Trainer> getAllTrainers() {
        return List.of(this.restTemplate.getForObject(this.url + "/trainers/", Trainer[].class));
    }

    @Override
    public List<Trainer> getAllTrainers(Principal principal) {
        return List.of(this.restTemplate.getForObject(this.url + "/trainers/", Trainer[].class)).stream().filter(trainer -> !trainer.getName().equals(principal.getName())).collect(Collectors.toList());
    }


    @Override
    public TrainerPokemonTypeWithLevel getTrainer(String name) {
        Trainer trainer =  this.restTemplate.getForObject(this.url + "/trainers/" + name, Trainer.class);
        List<PokemonTypeWithLevel> team = trainer.getTeam().stream().map(pokemon -> {
            PokemonType pokemonType = pokemonTypeService.getPokemonType(pokemon.getPokemonTypeId());
            return new PokemonTypeWithLevel(pokemon.getPokemonTypeId(),
                                            pokemon.getLevel(),
                                            pokemonType.getBaseExperience(),
                                            pokemonType.getHeight(),
                                            pokemonType.getName(),
                                            pokemonType.getSprites(),
                                            pokemonType.getStats(),
                                            pokemonType.getWeight(),
                                            pokemonType.getTypes());
        }).collect(Collectors.toList());

        return new TrainerPokemonTypeWithLevel(trainer, team);
    }

    @Qualifier("trainerApiRestTemplate")
    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Autowired
    void setPokemonTypeService(PokemonTypeService pokemonTypeService) {
        this.pokemonTypeService = pokemonTypeService;
    }

    @Value("${trainers.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
        this.url = pokemonServiceUrl;
    }

}
