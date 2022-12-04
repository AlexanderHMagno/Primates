package sanctuary.model.habitat;

import sanctuary.model.habitat.animals.AnimalAbstract;
import sanctuary.model.habitat.animals.AnimalCreator;
import sanctuary.model.habitat.animals.Animal;
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
     * Constructor of sanctuary
     * @param name This will be the name of this Sanctuary
     */
    public Sanctuary(String name) {
        this.enclosure = new Enclosure("Oasis - Enclosure");
        this.isolation = new Isolation(20, "Soteria - Isolation");
        this.name = name;
    }


    @Override
    public void addNewAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food)
            throws IllegalStateException, IllegalArgumentException  {

        if (this.getAnimalsNamesInHabitat().contains(AnimalAbstract.formatName(name))) {
            throw new IllegalStateException("An Animal with this name is already in the sanctuary");
        }

        Animal animal = AnimalCreator.createAnimal(name, species, sex,size, weight, age, food);

        this.isolation.addAnimal(animal);
    }


    @Override
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

    @Override
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
    public String getAnimalsInHabitat() {
        return toString();
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

    /**
     * Get animal Bio in a formatted string
     * @param species Search for a specific type
     * @param name Search for the animal by its name
     * @param location Char search for the animal i - isolation, e - enclosure
     * @return A formatted string with the animals information
     */
    protected String getAnimalBio(Species species, String name, char location) {
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

    @Override
    public String toString() {
        StringBuilder display = new StringBuilder("Welcome to " + name + "\nThis is the data information: \n");

        Housing[] houses = new Housing[]{this.isolation, this.enclosure};
        for (Housing home: houses) {
            if(home.getNumberOfAnimalsInHabitat() > 0) {
                display.append("\n").append(home.housingName).append("\n");
                display.append("Animals in Habitat:").append(home.getNumberOfAnimalsInHabitat()).append("\n");
                display.append(home.displayHabitatMembers());
            }
        }

        return display.toString();
    }
}
