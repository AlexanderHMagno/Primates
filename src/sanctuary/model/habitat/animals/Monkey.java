package sanctuary.model.habitat.animals;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

/**
 * IT represents a Monkey. it extends an AnimalAbstract class
 */
public class Monkey extends AnimalAbstract {

    /**
     * Constructor for a monkey. It has a name, species, sex, size, weight, age, food, health
     * a unique id, and a Home.
     * @param name - The name of the monkey
     * @param species - The Species of the monkey
     * @param sex - Gender of the monkey
     * @param size - Size in centimeters
     * @param weight - weight in kilos
     * @param age - provide the age of the monkey
     * @param food - provide the favorite food of this monkey
     * @throws  IllegalArgumentException If any argument doesnt follow guidelines
     */
    public Monkey(String name, Species species, Sex sex, double size, double weight, int age, Food food)
            throws IllegalArgumentException  {
        super(name,species,sex,size,weight,age,food);
    }

}
