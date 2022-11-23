package sanctuary.habitat;

import sanctuary.animals.AnimalCreator;
import sanctuary.animals.Animal;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Sanctuary implements Habitat {

    private final Enclosure enclosure;
    private final Isolation isolation;
    protected final String name;

    public Sanctuary(String name, Enclosure enclosure, Isolation isolation) {
        this.enclosure = enclosure;
        this.isolation = isolation;
        this.name = name;
    }

    public void addNewAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        Animal animal = AnimalCreator
                .createAnimal(name, species, sex,size, weight, age, food);
        this.isolation.addAnimal(animal);
    }

    public void moveAnimalToEnclosure(String name) {

        //Check the monkey is ok
        Animal monkey = this.isolation.getAnimal(name);

        if (monkey != null && !monkey.needsMedicalAttention()) {
            monkey = this.isolation.removeAnimal(monkey.getSpecies(),name);
            this.enclosure.addAnimal(monkey);
        }
    }

    public void moveAnimalToIsolation(Species species, String name) {
        //Check the monkey is ok
        Animal monkey = this.enclosure.getAnimal(species, name);
        if (monkey != null) {
            monkey = this.enclosure.removeAnimal(species, name);
            this.isolation.addAnimal(monkey);
        }
    }

    @Override
    public void printAnimalsInHabitat() {

        Housing[] houses = new Housing[]{this.isolation, this.enclosure};

        for (Housing home: houses) {
            if(home.getNumberOfAnimalsInHabitat() > 0) {
                System.out.println("\n" + home.habitatName);
                System.out.println("Primates in Habitat:" + home.getNumberOfAnimalsInHabitat());
                home.displayHabitatMembers();
            }
        }
    }


    @Override
    public void printAnimalsNamesInHabitat() {
        String[] isolated = this.isolation.getMembersNames();
        String[] enclosure = this.enclosure.getMembersNames();

        //Group isolated and enclosure monkeys
        ArrayList <String> names = new ArrayList<>(Arrays.asList(enclosure));
        Collections.addAll(names, isolated);

        //Print names in Ascending order
        System.out.println("\nDisplay animal's names in this sanctuary");
        for (Object animal: names.stream().sorted().toArray()) System.out.println(animal);
    }

    public void provideMedicalAttention (String name) {
        Animal animal = this.isolation.getAnimal(name);
        if (animal != null) animal.setHealth(100);
    }
}
