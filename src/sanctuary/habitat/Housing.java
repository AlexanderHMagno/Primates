package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.utils.Species;
import java.util.Arrays;

/**
 * This is an abstract class that represents the idea of a Housing, this will be used to create the spaces
 * to keep animals in the sanctuary. It has an habitatName.
 */
public abstract class Housing {

    protected String habitatName;

    /**
     * Print the animals in the house. It will provide the information as follows
     * Name, sex, Favorite food and Species
     */
    public abstract void displayHabitatMembers();

    /**
     * Get the number of animals in this location
     */
    public abstract int getNumberOfAnimalsInHabitat();

    /**
     * Get the animals in this location
     */
    protected abstract Animal[] getAnimals();

    /**
     *Add an animals in this location
     * @param animal representing the animal to be added
     * @returns true if the animal was correctly added to the location
     */
    public abstract boolean addAnimal(Animal animal);

    /**
     * Remove an animal for this location, name and species must be provided
     * to find the animal in the location
     * @param species Used to confirm the type of animal
     * @param name name of the animal, the one that was used to registered in the system
     *             names are final.
     * @return If the animal is found return it. Otherwise, null.
     */
    protected abstract Animal removeAnimal(Species species, String name);

    /**
     * Get the list of the names in this location.
     * @return a list of names of the animals in the location
     */
    protected String[] getMembersNames() {
        return Arrays.stream(this.getAnimals()).map(x -> x.getName()).
                toArray(String[]::new);
    }
}
