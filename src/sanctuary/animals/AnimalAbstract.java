package sanctuary.animals;

import sanctuary.habitat.Housing;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.UUID;

public abstract class AnimalAbstract implements Animal{

    protected final String name;
    protected final Species species;
    protected final Sex sex;
    protected final double size;
    protected double weight;
    protected int age;
    protected final Food food;
    protected int health;

    protected UUID id;
    protected Housing home;


    public AnimalAbstract(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        this.name = AnimalAbstract.formatName(name);
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

    public Species getSpecies() {
        return species;
    }

    public Sex getSex() {
        return sex;
    }

    public double getSize() {
        return size;
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

    protected void setAge(int age) {
        this.age = age;
    }

    public Food getFood() {return food;}

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public void setHome(Housing home) {this.home = home;}

    public boolean needsMedicalAttention() {
        return this.health < 80;
    }

    public static String formatName (String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }
}
