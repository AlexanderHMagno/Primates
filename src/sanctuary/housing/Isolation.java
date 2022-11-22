package sanctuary.housing;

import sanctuary.monkeys.Monkey;
import sanctuary.monkeys.Primate;

import java.util.Arrays;
import java.util.TreeMap;

public class Isolation extends Housing{

    private int maxInhabitants;
    private TreeMap<String, Primate> rooms;

    public Isolation(int maxInhabitants, String habitatName) {
        this.maxInhabitants = maxInhabitants;
        this.habitatName = habitatName;
        this.rooms = new TreeMap<>();
    }

    public boolean addPrimate(Primate monkey) {

        boolean added = false;

        //If Monkey needs medical attention
        if (this.numberOfEmptyRooms() > 0) {
            if (this.rooms.putIfAbsent(monkey.getName(),monkey) == null) {
                added = true;
            }
        }
        return added;
    }

    public Primate getPrimate(String name) {
        return this.rooms.get(Monkey.formatName(name));
    }
    public Primate removePrimate(String name) {
        return this.rooms.remove(Monkey.formatName(name));
    }

    public int getNumberOfMonkeysInHabitat() {
        return this.rooms.size();
    }

    public int numberOfEmptyRooms() {
        return this.maxInhabitants - this.getNumberOfMonkeysInHabitat();
    }

    public void displayHabitatMembers() {

        for (Primate ape : this.getMembers()) {
            System.out.println("Name: " + ape.getName() + " Sex:" + ape.getSex() + " Favorite food: " + ape.getFood());
        }

    }

    private Primate[] getMembers() {
        return this.rooms.values().toArray(new Primate[0]);
    }

    public String[] getMembersNames () {
        return Arrays.stream(this.getMembers()).map(x -> x.getName()).
                toArray(String[]::new);
    }

}
