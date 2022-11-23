package sanctuary.habitat;

import sanctuary.animals.Monkey;
import sanctuary.animals.Animal;
import sanctuary.utils.Species;
import java.util.TreeMap;

/**
 * This class represents an Isolation housing. It has a max number of inhabitants and room for them.
 */
public class Isolation extends Housing{

    private int maxInhabitants;
    private TreeMap<String, Animal> rooms;

    /**
     * Constructor for the Isolation class
     * @param maxInhabitants The maximum of animals to be held at the same time by the location
     * @param habitatName A name that represents this Housing
     */
    public Isolation(int maxInhabitants, String habitatName) {
        this.maxInhabitants = maxInhabitants;
        this.habitatName = habitatName;
        this.rooms = new TreeMap<>();
    }

    @Override
    public boolean addAnimal(Animal monkey) {

        if (this.numberOfEmptyRooms() > 0) {
            if (this.rooms.putIfAbsent(monkey.getName(),monkey) == null) {
                monkey.setHome(this);
                return true;
            }
        }
        return false;
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
    public void displayHabitatMembers() {

        for (Animal animal : this.getAnimals()) {
            System.out.println(
                    "Name: " + animal.getName() +
                    ", Sex:" + animal.getSex() +
                    ", Favorite food: " + animal.getFood()
                    + ", Species: " + animal.getSpecies());
        }

    }

    @Override
    protected Animal[] getAnimals() {
        return this.rooms.values().toArray(new Animal[0]);
    }

    /**
     * Obtain a one single animal.
     * @param name find the animal by its name
     * @return if Found the animal will be returned. Otherwise, null.
     */
    public Animal getAnimal(String name) {return this.rooms.get(Monkey.formatName(name));}

    /**
     * get the number of rooms that can provide room to the animals
     * @return difference between max number of inhabitants and the current animals in location
     */
    public int numberOfEmptyRooms() {
        return this.maxInhabitants - this.getNumberOfAnimalsInHabitat();
    }


}
