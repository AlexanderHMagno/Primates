package sanctuary.monkeys;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.UUID;

public class Monkey implements Primate {

    protected String name;
    protected final Species species;
    protected final Sex sex;
    protected double size;
    protected double weight;
    protected int age;
    protected Food food;
    protected int health;

    protected UUID id;


    public Monkey(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        this.name = Monkey.formatName(name);
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.weight = weight;
        this.age = age;
        this.food = food;
        this.health = 50;
        this.id = UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public Sex getSex() {
        return sex;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public boolean needsMedicalAttention() {
        return this.health < 80;
    }

    public static String formatName (String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

}
