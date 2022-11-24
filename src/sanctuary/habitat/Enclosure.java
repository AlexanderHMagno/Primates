package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

/**
 * This represents an Enclosure. It has a separate room for each type of Animal. It extends the housing abstract class
 */
public class Enclosure extends Housing {

    private final TreeMap<Species, TreeMap<String, Animal>> rooms;

    /**
     * Constructor for the enclosure Housing
     * @param habitatName Provide a name that represent this location
     */
    public Enclosure(String habitatName) {
        //Create an enclosure for each Animal
        this.rooms = new TreeMap<>();
        for (Species s : Species.values()) rooms.put(s, new TreeMap<>());
        this.housingName = habitatName;

    }


    @Override
    public boolean addAnimal(Animal animal) {

        //If Monkey doesn't need medical attention
        if (!animal.needsMedicalAttention()) {
            TreeMap<String, Animal> location = this.getEnclosure(animal.getSpecies());
            if (location.putIfAbsent(animal.getName(),animal) == null) {
                animal.setHome(this);
                return true;
            }
        }
        return false;
    }
    @Override
    protected Animal removeAnimal(Species species, String name) {
        return this.getEnclosure(species).remove(name);
    }
    @Override
    public void displayHabitatMembers() {

        for (Map.Entry<Species, TreeMap<String, Animal>> subgroup : this.rooms.entrySet()) {
            if (subgroup.getValue().size() > 0) {
                System.out.println("Species:" + subgroup.getKey());
                for (Map.Entry<String, Animal> entry : subgroup.getValue().entrySet()) {
                    System.out.println(
                            "Name: " + entry.getKey() +
                            " Sex:" + entry.getValue().getSex() +
                            " Favorite food: " + entry.getValue().getFood() +
                            ", Species: " + entry.getValue().getSpecies());
                }
            }
        }
    }
    @Override
    public int getNumberOfAnimalsInHabitat() {

        int totalMonkeys = 0;

        for (Map.Entry<Species, TreeMap<String, Animal>> subgroup : this.rooms.entrySet()) {
            totalMonkeys += subgroup.getValue().size();
        }
        return totalMonkeys;
    }

    @Override
    protected Animal[] getAnimals() {
        ArrayList<Animal> list = new ArrayList<>();
        for (Map.Entry<Species, TreeMap<String, Animal>> subgroup : this.rooms.entrySet()) {
            for (Map.Entry<String, Animal> entry : subgroup.getValue().entrySet()) {
                list.add(entry.getValue());
            }
        }
        return list.toArray(new Animal[0]);
    }

    /**
     * Obtain the information of a room (place where only one species of animal can live at the same time);
     * @param species Provide the species that you are looking for
     * @return Obtain the enclosure if found. Otherwise, null.
     */
    protected TreeMap<String, Animal> getEnclosure(Species species) {
        return rooms.get(species);
    }

    /**
     * Obtain a one single animal.
     * @param species Provide the room that you are looking for
     * @param name find the animal by its name
     * @return if Found the animal will be returned. Otherwise, null.
     */
    protected Animal getAnimal(Species species, String name) {
        return this.getEnclosure(species).get(name);
    }
}
