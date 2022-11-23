package sanctuary.habitat;

import sanctuary.animals.Monkey;
import sanctuary.animals.Animal;
import sanctuary.utils.Species;

import java.util.TreeMap;

public class Isolation extends Housing{

    private int maxInhabitants;
    private TreeMap<String, Animal> rooms;

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

    public Animal getAnimal(String name) {return this.rooms.get(Monkey.formatName(name));}
    public int numberOfEmptyRooms() {
        return this.maxInhabitants - this.getNumberOfAnimalsInHabitat();
    }


}
