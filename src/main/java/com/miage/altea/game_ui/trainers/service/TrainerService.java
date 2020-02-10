package com.miage.altea.game_ui.trainers.service;

import com.miage.altea.game_ui.bo.TrainerPokemonTypeWithLevel;
import com.miage.altea.game_ui.trainers.bo.Trainer;

import java.util.List;

public interface TrainerService {
    List<Trainer> getAllTrainers();
    TrainerPokemonTypeWithLevel getTrainer(String name);
}
