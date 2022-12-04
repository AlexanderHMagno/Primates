package sanctuary.model.habitat;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;

/**
 * This interface represents a Habitat that will hold a specific specie.
 */
public interface Habitat {

    /**
     * Produce a list for every enclosure that shows each individual animal that is currently housed there.
     * For each individual monkey, the list should include their name, sex, and favourite food.
     * @return a string with the information of this habitat.
     */
    String getAnimalsInHabitat();

    /**
     * Produce an alphabetical list (by name) of all  animals housed in the Sanctuary.
     * @return a list of names
     */
    ArrayList<String> getAnimalsNamesInHabitat(char location);

    String[] displayAnimalsInEnclosureGroup(Species species);

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

    /**
     * Add an animal to the Habitat
     * @param name Name to identify the animal. This will be used to register the animal in the Sanctuary
     * @param species The type of animal
     * @param sex - the gender of the animal
     * @param size - The height of the animal in Centimeters
     * @param weight - The Weight of the animal in Kilograms
     * @param age - age of the animal
     * @param food - Favorite Food of this animal
     * @throws IllegalStateException If the Shelter is already full
     * @throws IllegalArgumentException If the information of the animal is not complete and valid
     */
    void addNewAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food)
            throws IllegalStateException, IllegalArgumentException;

    /**
     * Move the animal from Isolation to Isolation
     * @param name the name that was used to register this animal
     * @throws IllegalStateException if the animal is not cured
     * @throws IllegalArgumentException if the animal doesn't exit in our db
     */
    void moveAnimalToEnclosure(String name) throws IllegalStateException, IllegalArgumentException;

    /**
     * Move the animal from Enclosure to Isolation
     * @param species species of animal to move
     * @param name the name that was used to register this animal
     * @throws IllegalStateException If the isolation room is full
     * @throws IllegalArgumentException if the animal doesn't exit in our db
     */
    void moveAnimalToIsolation(Species species, String name) throws IllegalStateException, IllegalArgumentException;

    String getAnimalBio(Species species, String text, char location);

    String getFavoriteFood(Species valueOf, String selectedValue);
}
