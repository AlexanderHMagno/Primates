package sanctuary.animals;

import sanctuary.habitat.Housing;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

/**
 * Interface to create any kind of animal
 */
public interface Animal {

    /**
     * get the name of the Animal
     * @return Animal's name
     */
    String getName();

    /**
     * get the favorite food of the Animal
     * @return Animal's favorite food
     */
    Food getFood();

    /**
     * get the gender the Animal
     * @return Animal's gender
     */
    Sex getSex();

    /**
     * get the animal's species
     * @return Animal's species
     */
    Species getSpecies();

    /**
     * Establish a home for this animal, we only handle animals with a housing
     */
    void setHome(Housing home);

    /**
     * Establish the health of this animal.
     */
    void setHealth(int health);

    /**
     * Do this animal needs medical attention
     * @return true if the animal will needs attention
     */
    boolean needsMedicalAttention ();

}
