package battles;

import arena.TypeComand;
import input.CreatePokemon;
import logger.Logger;
import logger.WriteDetails;
import trainer.Ability;
import trainer.Pokemon;
import trainer.Trainer;

public class BattlePokemon {
    private Trainer trainer1;
    private Trainer trainer2;
    private int bestPokemonTrainer1;
    private int bestPokemonTrainer2;
    private Logger logger;
    private boolean pokemon1Stun = false;
    private boolean pokemon2Stun = false;
    private int[][] cooldownPokemons;

    /**
     * Initialize an array of skills initial cooldown values to know when to use them
     */
    public void initCoolDown() {
        Pokemon pokemon1 = trainer1.getPokemons().get(bestPokemonTrainer1);
        Pokemon pokemon2 = trainer2.getPokemons().get(bestPokemonTrainer2);
        this.cooldownPokemons = new int[2][2];
        cooldownPokemons[0][0] = pokemon1.getAbility1().getCooldown();
        cooldownPokemons[0][1] = pokemon1.getAbility2().getCooldown();
        cooldownPokemons[1][0] = pokemon2.getAbility1().getCooldown();
        cooldownPokemons[1][1] = pokemon2.getAbility2().getCooldown();
    }

    public BattlePokemon(Trainer trainer1, Trainer trainer2,
                         int bestPokemonTrainer1, int bestPokemonTrainer2, Logger logger) {
        this.trainer1 = trainer1;
        this.trainer2 = trainer2;
        this.bestPokemonTrainer1 = bestPokemonTrainer1;
        this.bestPokemonTrainer2 = bestPokemonTrainer2;
        this.logger = logger;
    }

    /**
     * Generate copies of pokemon are generated so as not to affect their default
     * values. For each pokemon, a random event is generated. If the pokemon has not
     * been stunned, it is always checked if the opponent is performing a dodge ability.
     * When an ability is executed, the corresponding cooldown is checked. The logger
     * displays the status of the pokemon at all times, the ability used and whether they were stunned.
     */
    public  void fight() {
        Pokemon pokemon1 = CreatePokemon.getPokemon(trainer1, bestPokemonTrainer1);
        Pokemon pokemon2 = CreatePokemon.getPokemon(trainer2, bestPokemonTrainer2);
        initCoolDown();
        int time = 1;
        while (pokemon1.getHP() > 0 && pokemon2.getHP() > 0) {
            logger.publishResult(WriteDetails.roundNumber(time));
            TypeComand typeComandPokemon1 = TypeComand.getRandomComand();
            TypeComand typeComandPokemon2 = TypeComand.getRandomComand();

            if (!pokemon1Stun) {
                pokemon1Action(pokemon1, pokemon2, typeComandPokemon1, typeComandPokemon2);
            } else
                pokemon1Stun = false;

            if (!pokemon2Stun) {
                pokemon2Action(pokemon1, pokemon2, typeComandPokemon1, typeComandPokemon2);
            } else 
                pokemon2Stun = false;

            setStun(pokemon1, pokemon2, typeComandPokemon1, typeComandPokemon2);
            setStun(pokemon2, pokemon1, typeComandPokemon2, typeComandPokemon1);
            logger.publishResult(WriteDetails.statusPokemonVSPokemon(typeComandPokemon1,
                    pokemon1, typeComandPokemon2, pokemon2));
            time++;
        }
        checkWinner(pokemon1, pokemon2);
    }

    /**
     * Describe the possible actions for a pokemon if it is not stunned. Each time, the opponent is
     * checked for dodge, and when an ability is executed, the cooldown is checked.
     */
    private void pokemon1Action(Pokemon pokemon1, Pokemon pokemon2, TypeComand typeComandPokemon1,
                                TypeComand typeComandPokemon2) {
        if (typeComandPokemon1 == TypeComand.ABILITY1) {
            if (noDodge(typeComandPokemon2, pokemon2) &&
                    hasCoolDown(typeComandPokemon1, pokemon1, 0)) {
                Ability ability = getAbility(typeComandPokemon1, pokemon1);
                pokemon2.setHP(pokemon2.getHP() - ability.getDamage());
                logger.publishResult(WriteDetails.ability(ability));

            } else if (!hasCoolDown(typeComandPokemon1, pokemon1, 0))
                logger.publishResult(WriteDetails.abilityUnavailable(pokemon1.getAbility1().getCooldown()));

            pokemon1.getAbility1().setCooldown(pokemon1.getAbility1().getCooldown() - 1);
        } else if (typeComandPokemon1 == TypeComand.ABILITY2) {
            if (noDodge(typeComandPokemon2, pokemon2) &&
                    hasCoolDown(typeComandPokemon1, pokemon1, 0)) {
                Ability ability = getAbility(typeComandPokemon1, pokemon1);
                pokemon2.setHP(pokemon2.getHP() - ability.getDamage());
                logger.publishResult(WriteDetails.ability(ability));

            } else if (!hasCoolDown(typeComandPokemon1, pokemon1, 0))
                logger.publishResult(WriteDetails.abilityUnavailable(pokemon1.getAbility2().getCooldown()));

            }  else {
            if (noDodge(typeComandPokemon2, pokemon1))
                attack(pokemon1, pokemon2);

            pokemon1.getAbility2().setCooldown(pokemon1.getAbility1().getCooldown() - 1);
        }
    }

    /**
     * Describe the possible actions for a pokemon if it is not stunned. Each time, the opponent is
     * checked for dodge, and when an ability is executed, the cooldown is checked.
     */
    private void pokemon2Action(Pokemon pokemon1, Pokemon pokemon2, TypeComand typeComandPokemon1,
                                TypeComand typeComandPokemon2) {
        if (typeComandPokemon2 == TypeComand.ABILITY1) {
            if (noDodge(typeComandPokemon1, pokemon1) &&
                    hasCoolDown(typeComandPokemon2, pokemon2, 1)) {
                Ability ability = getAbility(typeComandPokemon2, pokemon2);
                pokemon1.setHP(pokemon1.getHP() - ability.getDamage());
                logger.publishResult(WriteDetails.ability(ability));

            } else if (!hasCoolDown(typeComandPokemon2, pokemon2, 1))
                logger.publishResult(WriteDetails.abilityUnavailable(pokemon2.getAbility1().getCooldown()));

            pokemon2.getAbility1().setCooldown(pokemon2.getAbility1().getCooldown() - 1);
        } else if (typeComandPokemon2 == TypeComand.ABILITY2) {
            if (noDodge(typeComandPokemon1, pokemon1) &&
                    hasCoolDown(typeComandPokemon2, pokemon2, 1)) {
                Ability ability = getAbility(typeComandPokemon2, pokemon2);
                pokemon1.setHP(pokemon1.getHP() - ability.getDamage());
                logger.publishResult(WriteDetails.ability(ability));

            } else if (!hasCoolDown(typeComandPokemon2, pokemon2, 1))
                logger.publishResult(WriteDetails.abilityUnavailable(pokemon2.getAbility2().getCooldown()));

            pokemon2.getAbility2().setCooldown(pokemon2.getAbility2().getCooldown() - 1);
        }  else {
            if (noDodge(typeComandPokemon1, pokemon1))
                attack(pokemon2, pokemon1);
        }
    }

    /**
     * If one of the pokemon has used a stunning ability, the corresponding field is set.
     */
    public void setStun(Pokemon pokemon1, Pokemon pokemon2, TypeComand typeComand1, TypeComand typeComand2) {
        if (typeComand1 == TypeComand.ABILITY1 && pokemon1.getAbility1().isStun()) {
            pokemon2Stun = true;
            logger.publishResult(WriteDetails.pokemonStun(pokemon2));
        }

        if (typeComand1 == TypeComand.ABILITY2 && pokemon1.getAbility2().isStun()) {
            pokemon2Stun = true;
            logger.publishResult(WriteDetails.pokemonStun(pokemon2));
        }
    }

    /**
     *
     Check if the activity can be used, if not, returns false.
     If it was the last time it could not be used, reset the cooldown.
     */
    public boolean hasCoolDown(TypeComand type, Pokemon pokemon, int index) {
        if (type == TypeComand.ABILITY1) {
            if (cooldownPokemons[index][0] != pokemon.getAbility1().getCooldown() &&
                    pokemon.getAbility1().getCooldown() > 1)
                return false;
            else if (pokemon.getAbility1().getCooldown() == 1) {
                pokemon.getAbility1().setCooldown(cooldownPokemons[index][0]);
                return true;
            } else if (cooldownPokemons[index][0] == pokemon.getAbility1().getCooldown())
                return true;
        }

        if (type == TypeComand.ABILITY2) {
            return cooldownPokemons[index][1] == pokemon.getAbility2().getCooldown() ||
                    pokemon.getAbility2().getCooldown() <= 0;
        }  else if (pokemon.getAbility2().getCooldown() == 1) {
            pokemon.getAbility2().setCooldown(cooldownPokemons[index][1]);
            return true;
        } else if (cooldownPokemons[index][1] == pokemon.getAbility2().getCooldown())
            return true;

        return true;
    }

    /**
     * Returns the ability of a Pokemon depending on the type of action
     */
    public Ability getAbility(TypeComand typeComand, Pokemon pokemon) {
        if (typeComand == TypeComand.ABILITY1)
            return pokemon.getAbility1();
        return pokemon.getAbility2();
    }

    /**
     * Check if the chosen action is an ability that contains dodge
     */
    public boolean noDodge(TypeComand typeComand, Pokemon pokemon) {
        if (typeComand != TypeComand.ABILITY1 && typeComand != TypeComand.ABILITY2)
            return true;

        else if (typeComand == TypeComand.ABILITY1 && pokemon.getAbility1().isDodge())
            return false;

        else return typeComand != TypeComand.ABILITY2 || !pokemon.getAbility2().isDodge();
    }

    /**
     * The first pokemon attacks the second, the hp of the second changes
     */
    public void attack(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.getNormalAttack() != Pokemon.NO_ATTACK)
            pokemon2.setHP(pokemon2.getHP() - pokemon1.getNormalAttack() + pokemon2.getDefense());
        else
            pokemon2.setHP(pokemon2.getHP() - pokemon1.getSpecialAttack() + pokemon2.getSpecialDefense());
    }

    public void checkWinner(Pokemon pokemon1, Pokemon pokemon2) {
        if (pokemon1.getHP() <= 0 && pokemon2.getHP() > 0) {
            logger.publishResult(WriteDetails.bestPokemon(trainer1, bestPokemonTrainer1));
        } else if (pokemon1.getHP() > 0 && pokemon2.getHP() <= 0) {
            logger.publishResult(WriteDetails.bestPokemon(trainer2, bestPokemonTrainer2));
        } else if (pokemon1.getHP() <= 0 && pokemon2.getHP() <= 0)
            logger.publishResult(WriteDetails.drawPolemonFight());
        }

    public Trainer getTrainer1() {
        return trainer1;
    }

    public void setTrainer1(Trainer trainer1) {
        this.trainer1 = trainer1;
    }

    public Trainer getTrainer2() {
        return trainer2;
    }

    public void setTrainer2(Trainer trainer2) {
        this.trainer2 = trainer2;
    }

    public int getBestPokemonTrainer1() {
        return bestPokemonTrainer1;
    }

    public void setBestPokemonTrainer1(int bestPokemonTrainer1) {
        this.bestPokemonTrainer1 = bestPokemonTrainer1;
    }

    public int getBestPokemonTrainer2() {
        return bestPokemonTrainer2;
    }

    public void setBestPokemonTrainer2(int bestPokemonTrainer2) {
        this.bestPokemonTrainer2 = bestPokemonTrainer2;
    }

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    public boolean isPokemon1Stun() {
        return pokemon1Stun;
    }

    public void setPokemon1Stun(boolean pokemon1Stun) {
        this.pokemon1Stun = pokemon1Stun;
    }

    public boolean isPokemon2Stun() {
        return pokemon2Stun;
    }

    public void setPokemon2Stun(boolean pokemon2Stun) {
        this.pokemon2Stun = pokemon2Stun;
    }

    public int[][] getCooldownPokemons() {
        return cooldownPokemons;
    }

    public void setCooldownPokemons(int[][] cooldownPokemons) {
        this.cooldownPokemons = cooldownPokemons;
    }
}
