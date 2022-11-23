package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class Enclosure extends Housing {

    private TreeMap<Species, TreeMap<String, Animal>> rooms;

    public Enclosure(String habitatName) {
        //Create an enclosure for each Animal
        this.rooms = new TreeMap<>();
        for (Species s : Species.values()) rooms.put(s, new TreeMap<>());
        this.habitatName = habitatName;

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
                    System.out.println("Name: " + entry.getKey() + " Sex:" + entry.getValue().getSex() + " Favorite food: " + entry.getValue().getFood());
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


    protected TreeMap<String, Animal> getEnclosure(Species species) {
        return rooms.get(species);
    }
    protected Animal getAnimal(Species species, String name) {
        return this.getEnclosure(species).get(name);
    }
}
