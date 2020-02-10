package com.miage.altea.game_ui.bo;

import com.miage.altea.game_ui.trainers.bo.Trainer;

import java.util.List;

public class TrainerPokemonTypeWithLevel {

    private Trainer trainer;
    private List<PokemonTypeWithLevel> team;

    public TrainerPokemonTypeWithLevel(Trainer trainer, List<PokemonTypeWithLevel> team) {
        this.trainer = trainer;
        this.team = team;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public List<PokemonTypeWithLevel> getTeam() {
        return team;
    }

    public void setTeam(List<PokemonTypeWithLevel> team) {
        this.team = team;
    }
}
