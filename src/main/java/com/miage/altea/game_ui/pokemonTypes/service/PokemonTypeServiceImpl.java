package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String url;

    @Override
    public List<PokemonType> listPokemonsTypes() {
        var pt = this.restTemplate.getForObject(this.url + "/pokemon-types/", PokemonType[].class);
        return List.of(pt);
    }

    @Override
    public PokemonType getPokemonType(int id) {
        return this.restTemplate.getForObject(this.url + "/pokemon-types/" + id, PokemonType.class);
    }

    @Autowired
    void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
       this.url = pokemonServiceUrl;
    }
}
