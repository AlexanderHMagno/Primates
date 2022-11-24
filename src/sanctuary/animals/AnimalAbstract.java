package sanctuary.animals;

import sanctuary.habitat.Housing;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import java.util.UUID;

/**
 * This class represents an abstract animal. It has a name, species, sex, size, weight, age, food, health
 * a unique id, and a Home. It implements an Animal interface.
 */
public abstract class AnimalAbstract implements Animal{

    protected final String name;
    protected final Species species;
    protected final Sex sex;
    protected final double size;
    protected final double weight;
    protected final int age;
    protected final Food food;
    protected int health;

    protected UUID id;
    protected Housing home;


    /**
     * Constructor for animal abstract classes. It has a name, species, sex, size, weight, age, food, health
     * a unique id, and a Home.
     * @param name - The name of the animal
     * @param species - The Species of the animal
     * @param sex - Gender of the animal
     * @param size - Size in centimeters
     * @param weight - weight in kilos
     * @param age - provide the age of the animal
     * @param food - provide the favorite food of this animal
     */
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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Species getSpecies() {
        return species;
    }

    @Override
    public Sex getSex() {
        return sex;
    }

    /**
     * Get the size of this animal
     * @return size of animal in centimeters
     */
    public double getSize() {
        return size;
    }


    /**
     * Get the weight of this animal
     * @return wight of animal in kilograms
     */
    public double getWeight() {
        return weight;
    }

    /**
     * Get Age of the animal
     * @return age's of animal
     */
    public int getAge() {
        return age;
    }

    @Override
    public Food getFood() {return food;}

    /**
     * Obtain the health of this animal
     * @return health status
     */
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void setHome(Housing home) {this.home = home;}

    @Override
    public boolean needsMedicalAttention() {
        return getHealth() >= 80;
    }

    /**
     * Format the name of the animal to provide a Capitalize name format - e.g. Alex.
     * @param name of the animal, it doesn't matter if it will be all lower or upper case.
     * @return the formatted name following a Capitalize case e.g. provided aLeX -> Alex
     */
    public static String formatName (String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
    }

    @Override
    public String toString() {
        return "AnimalAbstract{" +
                "name='" + getName() + '\'' +
                ", species=" + getSpecies() +
                ", sex=" + getSex() +
                ", size=" + getSize() +
                ", weight=" + getWeight() +
                ", age=" + getAge() +
                ", food=" + getFood() +
                ", health=" + getHealth() +
                ", id=" + id +
                ", home=" + home.getHouseName() +
                '}';
    }
}
