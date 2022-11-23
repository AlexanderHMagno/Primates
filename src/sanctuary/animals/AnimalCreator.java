package sanctuary.animals;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public class AnimalCreator {

    public  static Animal createAnimal(String name, Species species, Sex sex, double size,
                                       double weight, int age, Food food) {
        return new Monkey(name,species,sex,size,weight,age, food);
    }
}
