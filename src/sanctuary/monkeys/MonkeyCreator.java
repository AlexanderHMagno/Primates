package sanctuary.monkeys;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public class MonkeyCreator {

    public  static Primate createMonkey(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        return new Monkey(name,species,sex,size,weight,age, food);
    }
}
