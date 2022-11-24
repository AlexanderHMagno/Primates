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
     * @throws IllegalStateException If the Shelter is already full
     * @throws IllegalArgumentException If the information of the animal is not complete and valid
     */
    public void addNewAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food)
            throws IllegalStateException, IllegalArgumentException  {

        Animal animal = AnimalCreator.createAnimal(name, species, sex,size, weight, age, food);

        this.isolation.addAnimal(animal);
    }


    /**
     * Move the animal from Isolation to Isolation
     * @param name the name that was used to register this animal
     * @throws IllegalStateException if the animal is not cured
     * @throws IllegalArgumentException if the animal doesn't exit in our db
     */
    public void moveAnimalToEnclosure(String name) throws IllegalStateException, IllegalArgumentException {

        //Check the animal is ok
        Animal animal = this.isolation.getAnimal(name);

        if (animal == null) {
            throw new IllegalArgumentException("Animal is not in our Sanctuary");
        }
        if(animal.needsMedicalAttention()) {
            throw new IllegalStateException("Animal needs to be treated first");
        }
        animal = this.isolation.removeAnimal(animal.getSpecies(),name);
        this.enclosure.addAnimal(animal);

    }

    /**
     * Move the animal from Enclosure to Isolation
     * @param species species of animal to move
     * @param name the name that was used to register this animal
     * @throws IllegalStateException If the isolation room is full
     * @throws IllegalArgumentException if the animal doesn't exit in our db
     */
    public void moveAnimalToIsolation(Species species, String name) throws IllegalStateException, IllegalArgumentException {

        if(this.isolation.numberOfEmptyRooms() == 0) {
            throw new IllegalStateException("This animal can not be treated at the moment");
        }

        Animal animal = this.enclosure.getAnimal(species, name);
        if (animal != null) {
            animal = this.enclosure.removeAnimal(species, name);
            this.isolation.addAnimal(animal);
        }
    }

    @Override
    public int getNumberOfAnimals(char location) {
        switch (location){
            case('i'):
                return this.isolation.getNumberOfAnimalsInHabitat();
            case('e'):
                return this.enclosure.getNumberOfAnimalsInHabitat();
            default:
                return this.isolation.getNumberOfAnimalsInHabitat() + this.enclosure.getNumberOfAnimalsInHabitat();
        }
    }

    @Override
    public void printAnimalsInHabitat() {

        System.out.println("Welcome to " + name + "\nThis is the data information: \n");

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
    public ArrayList<String> getAnimalsNamesInHabitat() {
        String[] isolated = this.isolation.getMembersNames();
        String[] enclosure = this.enclosure.getMembersNames();

        //Group isolated and enclosure monkeys
        ArrayList <String> names = new ArrayList<>(Arrays.asList(enclosure));
        Collections.addAll(names, isolated);
        Collections.sort(names);
        return names;
    }

    @Override
    public void provideMedicalAttention (String name) throws IllegalArgumentException {
        Animal animal = this.isolation.getAnimal(name);
        animal.setHealth(100);
    }

    public String getAnimalBio(Species species, String name, char location) {
        Animal animal = null;
        if (location == 'i') {
            animal = this.isolation.getAnimal(name);
        } else if (location == 'e') {
            animal = this.enclosure.getAnimal(species,name);
        }

        return animal != null ? animal.toString() : "No Animal";
    }

    /**
     * Helper method to run as a test, this will cure and move all animals
     * to the enclosure area
     */
    public void moveForceToEnclosure() {
        this.provideMedicalAttentionAll();
        this.moveAllAnimalToEnclosure();
    }

    /**
     * Cure Magically all animals in this Isolation
     */
    private void provideMedicalAttentionAll () {

        String[] isolated = this.isolation.getMembersNames();
        Arrays.stream(isolated).forEach(x -> {
            Animal animal = this.isolation.getAnimal(x);
            animal.setHealth(100);
        });
    }

    /**
     * Move all animals from isolation to enclosure
     */
    private void moveAllAnimalToEnclosure () {
        String[] isolated = this.isolation.getMembersNames();
        Arrays.stream(isolated).forEach(this::moveAnimalToEnclosure);
    }
}
