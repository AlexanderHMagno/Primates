package sanctuary.model.habitat.animals;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

/**
 * Class to create any king of animal.
 */
public class AnimalCreator {

    /**
     * Create an animal classes. It has a name, species, sex, size, weight, age, food, health
     * a unique id, and a Home.
     * @param name - The name of the animal
     * @param species - The Species of the animal
     * @param sex - Gender of the animal
     * @param size - Size in centimeters
     * @param weight - weight in kilos
     * @param age - provide the age of the animal
     * @param food - provide the favorite food of this animal
     * @throws IllegalArgumentException If the information is not correct
     */
    public static Animal createAnimal(String name, Species species, Sex sex, double size,
                                       double weight, int age, Food food) throws IllegalArgumentException  {
        return new Monkey(name,species,sex,size,weight,age, food);
    }
}
