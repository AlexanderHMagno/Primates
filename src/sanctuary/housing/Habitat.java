package sanctuary.housing;

public interface Habitat {


    /**
     * Produce a list for every enclosure that shows each individual monkey that is currently housed there.
     * For each individual monkey, the list should include their name, sex, and favourite food.
     */
    void printMonkeysInHabitat();

    /**
     * Produce an alphabetical list (by name) of all of the monkeys housed in the Sanctuary.
     */
    void printMonkeysNamesInHabitat();
}
