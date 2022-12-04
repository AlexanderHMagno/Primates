package sanctuary.controller;

import sanctuary.model.habitat.animals.Animal;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;

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
     * @return true if the animal was added to the system
     * @throws IllegalStateException If the Shelter is already full
     * @throws IllegalArgumentException If the information of the animal is not complete and valid
     */
    boolean addAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food)
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
     * Provide a list of all animals in enclosure
     * @return a list with all animals in enclosure
     */
    ArrayList<Animal> displayAnimalsInEnclosure();

    /**
     * Provide a list of all animals in isolation
     * @return
     */
    ArrayList<Animal> displayAnimalsInIsolation();

    /**
     * Provide a list of all animals in sanctuary by name
     * @return
     */
    ArrayList<String> displayAllAnimalsNames(char location);


    /**
     * Close this system
     */
    void closeProgram();

    void updateDashboard();

    /**
     * Run the program
     */
    void go();


    void updateIsolation();

    void updateEnclosure();

    String getAnimalBio(Species species,String text, char location);

    String[] displayAnimalsInEnclosureGroup(Species species);

    String getFavoriteFood(Species valueOf, String selectedValue);
}
