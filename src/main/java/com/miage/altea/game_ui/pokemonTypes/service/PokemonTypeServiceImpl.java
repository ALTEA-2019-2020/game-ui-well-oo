package com.miage.altea.game_ui.pokemonTypes.service;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.retry.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService {

    private RestTemplate restTemplate;
    private String url;
    private CircuitBreaker circuitBreaker;
    private Retry retry;

    @Override
    @Cacheable("pokemon-types")
    public List<PokemonType> listPokemonsTypes() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, String.valueOf(LocaleContextHolder.getLocale()));
        HttpEntity entity = new HttpEntity(headers);
        ResponseEntity<PokemonType[]> response = this.restTemplate.exchange(this.url + "/pokemon-types/", HttpMethod.GET, entity, PokemonType[].class);
        return List.of(response.getBody());
    }


    @Override
    @Cacheable("pokemon-types")
    public PokemonType getPokemonType(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT_LANGUAGE, String.valueOf(LocaleContextHolder.getLocale()));
        HttpEntity entity = new HttpEntity(headers);
        return this.retry.executeSupplier(() -> this.restTemplate.exchange(this.url + "/pokemon-types/" + id, HttpMethod.GET, entity, PokemonType.class).getBody());
    }

    @Qualifier("restTemplate")
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Value("${pokemonType.service.url}")
    void setPokemonTypeServiceUrl(String pokemonServiceUrl) {
       this.url = pokemonServiceUrl;
    }

    @Autowired
    public void setCircuitBreaker(CircuitBreaker circuitBreaker) {
        this.circuitBreaker = circuitBreaker;
    }

    @Autowired
    public void setRetry(Retry retry) {
        this.retry = retry;
    }
}
