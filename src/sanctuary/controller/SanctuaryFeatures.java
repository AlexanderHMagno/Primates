package sanctuary.controller;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import java.util.ArrayList;

/**
 * This interface represent the Controller in the MVC architect design.
 */
public interface SanctuaryFeatures {

    /**
     * Action to move animal to the enclosure
     * @param name - The name of the monkey
     * @param species - The Species of the monkey
     * @param sex - Gender of the monkey
     * @param size - Size in centimeters
     * @param weight - weight in kilos
     * @param age - provide the age of the monkey
     * @param food - provide the favorite food of this monkey
     * @throws IllegalStateException If the Shelter is already full
     * @throws IllegalArgumentException If the information of the animal is not complete and valid
     */
    void addAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food)
            throws IllegalStateException, IllegalArgumentException;

    /**
     * Transfer an animal to enclosure from isolation
     * @param animal name of the animal to move to the enclosure.
     * @throws IllegalStateException The animal is not healthy
     * @throws IllegalArgumentException the animal is not register with us
     */
    void transferAnimalToEnclosure(String animal) throws IllegalStateException, IllegalArgumentException;

    /**
     * Transfer an animal to  isolation from enclosure
     * @param animal name of the animal to move to the Isolation.
     * @throws IllegalArgumentException the animal is not register with us
     */
    void transferAnimalToIsolation(Species species,String animal) throws IllegalArgumentException ;

    /**
     * Provide medical attention
     * @param animal name of the animal to be treated.
     * @throws IllegalArgumentException the animal is not in isolation
     */
    void provideMedicalAttention(String animal);

    /**
     * Provide a list of all animals in sanctuary by name
     * @return a list of animal's names
     */
    ArrayList<String> displayAllAnimalsNames(char location);

    /**
     * Update the dashboard panel information
     */
    void updateDashboard();

    /**
     * Run the program
     */
    void go();

    /**
     * Update the isolation panel
     */
    void updateIsolation();

    /**
     * Update the enclosure panel
     */
    void updateEnclosure();

    /**
     * Get the animal's bio.
     * @param species specie to search in habitat
     * @param text name of the animal
     * @param location letter to indicate what room to search 'i' isolation, 'e' enclosure
     * @return the bio of the animal
     */
    String getAnimalBio(Species species,String text, char location);

    /**
     * Display the name of the animals in the enclosure per specie
     * @param species subHabitat to search
     * @return names of animal in a particular subHabitat
     */
    String[] displayAnimalsInEnclosureGroup(Species species);

    /**
     * Obtain the favorite food
     * @param species specie to search in habitat
     * @param name name of the animal
     * @return name of the favorite food
     */
    String getFavoriteFood(Species species, String name);

    /**
     * Obtain an animal by name
     * @param searched name of the animal
     * @return an array with the name of the habitat as first place, and the bio of the animal on second place
     */
    String[] searchAnimalByName(String searched);

    /**
     * Obtain the name of the sanctuary
     * @return Name of the sanctuary
     */
    String sanctuaryName();

    /**
     * Number of animals in enclosure
     * @param species By specie
     * @return return number of animals by specie
     */
    int animalInEnclosure(Species species);

    /**
     * Allow to load massive animals to isolation triggered by code
     * @throws IllegalStateException if isolation is full
     */
    void massiveLoader() throws IllegalStateException;

    /**
     * Allow to heal several animals at the same time and move to enclosure triggered by code
     */
    void massiveHealer();
}
