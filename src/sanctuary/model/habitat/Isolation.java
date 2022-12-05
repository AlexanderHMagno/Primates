package sanctuary.model.habitat;

import sanctuary.model.habitat.animals.Monkey;
import sanctuary.model.habitat.animals.Animal;
import sanctuary.utils.Species;
import java.util.TreeMap;

/**
 * This class represents an Isolation housing. It has a max number of inhabitants and room for them.
 */
public class Isolation extends Housing{

    private int maxInhabitants;
    private final TreeMap<String, Animal> rooms;

    /**
     * Constructor for the Isolation class
     * @param maxInhabitants The maximum of animals to be held at the same time by the location
     * @param habitatName A name that represents this Housing
     */
    public Isolation(int maxInhabitants, String habitatName) {
        this.housingName = habitatName;
        this.rooms = new TreeMap<>();
        this.setMaxInhabitants(maxInhabitants);
    }

    @Override
    public void addAnimal(Animal monkey) throws IllegalStateException {

        if(this.numberOfEmptyRooms() == 0) {
            throw new IllegalStateException("Sorry, we are full at the moment and we can't accept more Animals");
        }

        if (this.rooms.putIfAbsent(monkey.getName(),monkey) == null) {
            monkey.setHome(this);
        } else {
            throw new IllegalStateException("There is another animal with the same name please provide a different");
        }
    }

    @Override
    protected Animal removeAnimal(Species species, String name) {
        return this.rooms.remove(Monkey.formatName(name));
    }
    @Override
    public int getNumberOfAnimalsInHabitat() {
        return this.rooms.size();
    }

    @Override
    public String displayHabitatMembers() {

        String info = "";

        for (Animal animal : this.getAnimals()) {
            info +=(
                    "Name: " + animal.getName() +
                    ", Sex:" + animal.getSex() +
                    ", Favorite food: " + animal.getFood()
                    + ", Species: " + animal.getSpecies()
                    + "\n"
            );
        }
        return info;
    }

    @Override
    protected Animal[] getAnimals() {
        return this.rooms.values().toArray(new Animal[0]);
    }

    @Override
    protected Animal[] getAnimalsEnclosure(Species species) {
        return new Animal[0];
    }

    /**
     * Obtain a one single animal.
     * @param name find the animal by its name
     * @return if Found the animal will be returned. Otherwise, null.
     * @throws IllegalArgumentException If animal is not in this location
     */
    protected Animal getAnimal(String name) throws IllegalArgumentException {
        if (name.trim().length() == 0) throw new IllegalArgumentException("No Animal Detected");
        Animal animal = this.rooms.get(Monkey.formatName(name));

        if (animal == null) throw new IllegalArgumentException("Animal is not in our Sanctuary");
        return animal;
    }

    /**
     * get the number of rooms that can provide room to the animals
     * @return difference between max number of inhabitants and the current animals in location
     */
    protected int numberOfEmptyRooms() {
        return this.maxInhabitants - this.getNumberOfAnimalsInHabitat();
    }

    /**
     * Increase or decrease the size of animals that can be held at the same time
     * @param max number of animals must be greater than the current number of animals
     */
    protected void setMaxInhabitants(int max) {
        if (max > this.rooms.size()) {
            this.maxInhabitants = max;
        }
    }

    public String getAnimalBioByName(String name) {
        return "Isolation";
    }
}
