package sanctuary.model.habitat;

import sanctuary.model.habitat.animals.Animal;
import sanctuary.model.habitat.animals.Monkey;
import sanctuary.utils.Species;

import java.util.ArrayList;
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

    /**
     * Add a new animal to the enclosure
     * @param animal representing the animal to be added
     * @throws IllegalStateException If the animal still needs attention
     */
    @Override
    public void addAnimal(Animal animal) throws IllegalStateException {

        //If Monkey doesn't need medical attention
        if (animal.needsMedicalAttention()) {
            throw  new IllegalStateException("Animal needs attention first");
        }

        TreeMap<String, Animal> location = this.getEnclosure(animal.getSpecies());
        if (location.putIfAbsent(animal.getName(),animal) == null) {
            animal.setHome(this);
        } else {
            throw new IllegalStateException("There is another animal with the same name please provide a different");
        }
    }
    @Override
    protected Animal removeAnimal(Species species, String name) {
        return this.getEnclosure(species).remove(name);
    }
    @Override
    public String displayHabitatMembers() {

        StringBuilder info = new StringBuilder();
        for (Map.Entry<Species, TreeMap<String, Animal>> subgroup : this.rooms.entrySet()) {
            if (subgroup.getValue().size() > 0) {
                info.append("Species:").append(subgroup.getKey()).append("\n\n");
                for (Map.Entry<String, Animal> entry : subgroup.getValue().entrySet()) {
                    info.append("Name: ")
                            .append(entry.getKey())
                            .append(" Sex:")
                            .append(entry.getValue().getSex())
                            .append(" Favorite food: ")
                            .append(entry.getValue().getFood())
                            .append(", Species: ")
                            .append(entry.getValue().getSpecies())
                            .append("\n");
                }
                info.append("\n");
            }
        }
        return info.toString();
    }
    @Override
    public int getNumberOfAnimalsInHabitat() {

        int totalMonkeys = 0;

        for (Map.Entry<Species, TreeMap<String, Animal>> subgroup : this.rooms.entrySet()) {
            totalMonkeys += subgroup.getValue().size();
        }
        return totalMonkeys;
    }

    public String getAnimalBioByName(String name) throws IllegalArgumentException {

        String nameF = Monkey.formatName(name);
        Animal[] allAnimals = this.getAnimals();

        for (Animal animal : allAnimals) {
            if (nameF.equals(animal.getName()))return animal.toString();
        }

        return "Not Found";
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

    @Override
    protected Animal[] getAnimalsEnclosure(Species species) {
        ArrayList<Animal> list = new ArrayList<>();

        for (Map.Entry<String, Animal> entry : this.getEnclosure(species).entrySet()) {
            list.add(entry.getValue());
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
     * @throws IllegalArgumentException If animal is not in this location
     */
    protected Animal getAnimal(Species species, String name) throws IllegalArgumentException {

        Animal animal = this.getEnclosure(species).get(name);
        if (animal == null) throw new IllegalArgumentException("Animal is not in our Sanctuary");
        return animal;
    }
}
