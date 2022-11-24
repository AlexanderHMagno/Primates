package sanctuary.habitat;

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
     */
    void printAnimalsNamesInHabitat();

    /**
     * Increase the health of an animal
     * @param name the name that it was used to register the animal
     */
    void provideMedicalAttention (String name);

}
