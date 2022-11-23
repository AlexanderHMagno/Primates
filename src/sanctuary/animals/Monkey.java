package sanctuary.animals;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;


public class Monkey extends AnimalAbstract {

    public Monkey(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        super(name,species,sex,size,weight,age,food);
    }

}
