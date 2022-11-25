package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.utils.Species;
import java.util.Arrays;

/**
 * This is an abstract class that represents the idea of a Housing, this will be used to create the spaces
 * to keep animals in the sanctuary. It has an housingName.
 */
public abstract class Housing {

    protected String housingName;

    /**
     * Print the animals in the house. It will provide the information as follows
     * Name, sex, Favorite food and Species
     * @return list of each animal
     */
    public abstract String displayHabitatMembers();

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
     * @throws IllegalStateException If the location can not receive more animals
     *         If there is another animal with the same name in the house
     */
    public abstract void addAnimal(Animal animal) throws IllegalStateException;

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
        return Arrays.stream(this.getAnimals()).map(Animal::getName).toArray(String[]::new);
    }

    /**
     * get the name of this house
     * @return The name of this house
     */
    public String getHouseName() {
        return this.housingName;
    }
}
