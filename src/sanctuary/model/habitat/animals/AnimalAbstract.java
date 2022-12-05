package sanctuary.model.habitat.animals;

import sanctuary.model.habitat.Housing;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;


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
     * @throws IllegalArgumentException If the information is not correct
     */
    public AnimalAbstract(String name, Species species, Sex sex,
                          double size, double weight, int age, Food food) throws IllegalArgumentException  {

        if (name.trim().length() == 0) throw new IllegalArgumentException("Please, Provide a valid and unique name");
        if (size < 0) throw new IllegalArgumentException("Provide a valid size");
        if (weight < 0) throw new IllegalArgumentException("Provide a valid weight");
        if (age < 0) throw new IllegalArgumentException("Provide a valid age");

        this.name = AnimalAbstract.formatName(name);
        this.species = species;
        this.sex = sex;
        this.size = size;
        this.weight = weight;
        this.age = age;
        this.food = food;
        this.health = 50;
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
    public void setHealth(int health) throws IllegalArgumentException {
        if (health < 0) throw new IllegalArgumentException("Health can be only a positive value");
        this.health = health;
    }

    @Override
    public void setHome(Housing home) {this.home = home;}

    @Override
    public String getHomeName() {
        return (home != null ? home.getHouseName() : "Wild animal");
    }
    @Override
    public boolean needsMedicalAttention() {
        return getHealth() <= 80;
    }

    /**
     * Format the name of the animal to provide a Capitalize name format - e.g. Alex.
     * Note: This method should live in a different class but for now lets keep it here.
     * @param name of the animal, it doesn't matter if it will be all lower or upper case.
     * @return the formatted name following a Capitalize case e.g. provided aLeX -> Alex
     */
    public static String formatName (String name) throws IllegalArgumentException {
        if (name.length() == 0) throw new IllegalArgumentException();
        String formatted = name.substring(0, 1).toUpperCase() ;
        if (name.length() > 1) {
            formatted += name.substring(1).toLowerCase();
        }
        return formatted.trim();
    }

    @Override
    public String toString() {
        return
                "\nName: " + getName() +
                ",\nSpecies: " + getSpecies() +
                ",\nSex: " + getSex() +
                ",\nSize: " + getSize() +
                ",\nWeight: " + getWeight() +
                ",\nAge: " + getAge() +
                ",\nFood: " + getFood() +
                ",\nHealth: " + getHealth() +
                ",\nHome: " + getHomeName() +
                "\n";
    }
}
