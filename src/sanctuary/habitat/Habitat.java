package sanctuary.habitat;

import java.util.ArrayList;

/**
 * This interface represents a Habitat that will hold a specific specie.
 */
public interface Habitat {

    /**
     * Produce a list for every enclosure that shows each individual monkey that is currently housed there.
     * For each individual monkey, the list should include their name, sex, and favourite food.
     */
    void printAnimalsInHabitat();

    /**
     * Produce an alphabetical list (by name) of all  animals housed in the Sanctuary.
     *
     * @return
     */
    ArrayList<String> getAnimalsNamesInHabitat();

    /**
     * Increase the health of an animal
     * @param name the name that it was used to register the animal
     * @throws IllegalArgumentException If the animal is not in Sanctuary
     */
    void provideMedicalAttention (String name) throws IllegalArgumentException;

    /**
     * Get the number of animals in this Habitat
     * @param location provide i for isolation, e for enclosure, t for total
     * @return the total animals in a room or total in sanctuary
     */
    int getNumberOfAnimals(char location);

}
