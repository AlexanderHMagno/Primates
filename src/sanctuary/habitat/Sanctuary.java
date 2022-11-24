package sanctuary.habitat;

import sanctuary.animals.AnimalCreator;
import sanctuary.animals.Animal;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * This class represents A Sanctuary. It has Housing for the animals in 2 main sections Enclosure and Isolation, and
 * it has a name. It implements the Habitat Interface.
 */
public class Sanctuary implements Habitat {

    private final Enclosure enclosure;
    private final Isolation isolation;
    protected final String name;

    /**
     * Constructor of sanctuary
     * @param name This will be the name of this Sanctuary
     * @param enclosure Provides a Habitat to keep these animals in a familiar ambient
     * @param isolation Provides a Habitat to keep these animals for isolation or treatment
     */
    public Sanctuary(String name, Enclosure enclosure, Isolation isolation) {
        this.enclosure = enclosure;
        this.isolation = isolation;
        this.name = name;
    }

    /**
     * Add an animal to the sanctuary
     * @param name Name to identify the animal. This will be used to register the animal in the Sanctuary
     * @param species The type of animal
     * @param sex - the gender of the animal
     * @param size - The height of the animal in Centimeters
     * @param weight - The Weight of the animal in Kilograms
     * @param age - age of the animal
     * @param food - Favorite Food of this animal
     */
    public void addNewAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        Animal animal = AnimalCreator
                .createAnimal(name, species, sex,size, weight, age, food);
        this.isolation.addAnimal(animal);
    }


    /**
     * Move the animal from Isolation to Isolation
     * @param name the name that was used to register this animal
     */
    public void moveAnimalToEnclosure(String name) {

        //Check the monkey is ok
        Animal monkey = this.isolation.getAnimal(name);

        if (monkey != null && monkey.needsMedicalAttention()) {
            monkey = this.isolation.removeAnimal(monkey.getSpecies(),name);
            this.enclosure.addAnimal(monkey);
        }
    }

    /**
     * Move the animal from Enclosure to Isolation
     * @param species species of animal to move
     * @param name the name that was used to register this animal
     */
    public void moveAnimalToIsolation(Species species, String name) {
        //Check the monkey is ok
        Animal monkey = this.enclosure.getAnimal(species, name);
        if (monkey != null) {
            monkey = this.enclosure.removeAnimal(species, name);
            this.isolation.addAnimal(monkey);
        }
    }

    @Override
    public void printAnimalsInHabitat() {

        Housing[] houses = new Housing[]{this.isolation, this.enclosure};

        for (Housing home: houses) {
            if(home.getNumberOfAnimalsInHabitat() > 0) {
                System.out.println("\n" + home.housingName);
                System.out.println("Primates in Habitat:" + home.getNumberOfAnimalsInHabitat());
                home.displayHabitatMembers();
            }
        }
    }

    @Override
    public void printAnimalsNamesInHabitat() {
        String[] isolated = this.isolation.getMembersNames();
        String[] enclosure = this.enclosure.getMembersNames();

        //Group isolated and enclosure monkeys
        ArrayList <String> names = new ArrayList<>(Arrays.asList(enclosure));
        Collections.addAll(names, isolated);

        //Print names in Ascending order
        System.out.println("\nDisplay animal's names in this sanctuary");
        for (Object animal: names.stream().sorted().toArray()) System.out.println(animal);
    }

    @Override
    public void provideMedicalAttention (String name) {
        Animal animal = this.isolation.getAnimal(name);
        if (animal != null) animal.setHealth(100);
    }
}
