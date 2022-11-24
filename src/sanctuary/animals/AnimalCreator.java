package sanctuary.animals;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

/**
 * Class to create any king of animal.
 */
public class AnimalCreator {

    public static Animal createAnimal(String name, Species species, Sex sex, double size,
                                       double weight, int age, Food food) throws IllegalArgumentException  {
        return new Monkey(name,species,sex,size,weight,age, food);
    }
}
