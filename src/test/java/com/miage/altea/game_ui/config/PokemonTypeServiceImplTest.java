package com.miage.altea.game_ui.config;

import com.miage.altea.game_ui.pokemonTypes.bo.PokemonType;
import com.miage.altea.game_ui.pokemonTypes.service.PokemonTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PokemonTypeServiceImplTest {

    @Autowired
    PokemonTypeService pokemonTypeService;

    @Qualifier("restTemplate")
    @MockBean
    RestTemplate restTemplate;

    @Value("${pokemonType.service.url}/pokemon-types/{id}")
    String expectedUrl;

    @Autowired
    CacheManager cacheManager;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        pikachu.setName("Pikachu");

        when(restTemplate.exchange(any(String.class), any(), any(), eq(PokemonType.class))).thenReturn(new ResponseEntity(pikachu, HttpStatus.ACCEPTED));
    }

    @Test
    void getPokemonType_shouldUseCache() {
        pokemonTypeService.getPokemonType(25);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put("id", "25");
        var builder = UriComponentsBuilder.fromUriString(expectedUrl).buildAndExpand(urlParams).toUriString();

        // rest template should have been called once
        verify(restTemplate).exchange(eq(builder), any(), any(), eq(PokemonType.class));

        pokemonTypeService.getPokemonType(25);

        // rest template should not be called anymore because result is in cache !
        verifyNoMoreInteractions(restTemplate);

        // one result should be in cache !
        var cachedValue = cacheManager.getCache("pokemon-types").get(25).get();
        assertNotNull(cachedValue);
        assertEquals(PokemonType.class, cachedValue.getClass());
        assertEquals("Pikachu", ((PokemonType)cachedValue).getName());
    }

}
