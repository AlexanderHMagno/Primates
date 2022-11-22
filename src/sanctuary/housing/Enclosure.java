package sanctuary.housing;

import sanctuary.monkeys.Monkey;
import sanctuary.monkeys.Primate;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Enclosure extends Housing {

    protected TreeMap<Species, TreeMap<String, Primate>> rooms = new TreeMap<Species, TreeMap<String, Primate>>();


    public Enclosure(String habitatName) {
        //Create an enclosure for each Primate
        for (Species s : Species.values()) {
            rooms.put(s, new TreeMap<String, Primate>());
        }
        this.habitatName = habitatName;
    }

    protected TreeMap<String, Primate> getEnclosure(Species species) {
        return rooms.get(species);
    }

    public boolean addPrimate(Primate monkey) {

        boolean added = false;

        //If Monkey doesn't need medical attention
        if (!monkey.needsMedicalAttention()) {
            TreeMap<String, Primate> location = this.getEnclosure(monkey.getSpecies());
            if (location.putIfAbsent(monkey.getName(),monkey) == null) {
                added = true;
            }
        }
        return added;
    }

    public Primate removePrimate(Species species, String name) {

        TreeMap<String, Primate> location = this.getEnclosure(species);
        return location.get(name);
    }

    public void displayHabitatMembers() {

        for (Map.Entry<Species, TreeMap<String, Primate>> subgroup : this.rooms.entrySet()) {
            System.out.println("\nHabitat:" + subgroup.getKey());
            if (subgroup.getValue().size() == 0) System.out.println("No Monkeys in this habitat\n");
            for (Map.Entry<String, Primate> entry : subgroup.getValue().entrySet()) {
                System.out.println("Name: " + entry.getKey() + " Sex:" + entry.getValue().getSex() + " Favorite food: " + entry.getValue().getFood());
            }
        }
    }

    public int getNumberOfMonkeysInHabitat() {

        int totalMonkeys = 0;

        for (Map.Entry<Species, TreeMap<String, Primate>> subgroup : this.rooms.entrySet()) {
            totalMonkeys += subgroup.getValue().size();
        }
        return totalMonkeys;
    }

    private Primate[] getMembers () {

        ArrayList<Primate> list = new ArrayList<>();

        for (Map.Entry<Species, TreeMap<String, Primate>> subgroup : this.rooms.entrySet()) {
            for (Map.Entry<String, Primate> entry : subgroup.getValue().entrySet()) {
                list.add(entry.getValue());
            }
        }

        return list.toArray(new Primate[0]);
    }

    public String[] getMembersNames() {

        return Arrays.stream(this.getMembers()).map(x -> x.getName()).
                toArray(String[]::new);
    }
}
