package sanctuary.housing;

import sanctuary.monkeys.MonkeyCreator;
import sanctuary.monkeys.Primate;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sanctuary implements Habitat{

    private final Enclosure enclosure;
    private final Isolation isolation;
    protected final String name;

    public Sanctuary(String name, Enclosure enclosure, Isolation isolation) {
        this.enclosure = enclosure;
        this.isolation = isolation;
        this.name = name;
    }

    public void addNewMonkey(String name, Species species, Sex sex, double size, double weight, int age, Food food) {
        Primate monkey = MonkeyCreator
                .createMonkey(name, species, sex,size, weight, age, food);
        this.isolation.addPrimate(monkey);
    }

    public void moveMonkeyToEnclosure(String name) {

        //Check the monkey is ok
        Primate monkey = this.isolation.getPrimate(name);

        if (monkey != null && !monkey.needsMedicalAttention()) {
            monkey = this.isolation.removePrimate(name);
            this.enclosure.addPrimate(monkey);
        }
    }

    public void moveMonkeyToIsolation(String name) {

        //Check the monkey is ok
        Primate monkey = this.isolation.getPrimate(name);

        if (monkey != null && !monkey.needsMedicalAttention()) {
            monkey = this.isolation.removePrimate(name);
            this.enclosure.addPrimate(monkey);
        }
    }

    @Override
    public void printMonkeysInHabitat() {

        if(this.isolation.getNumberOfMonkeysInHabitat() > 0) {
            System.out.println(this.isolation.habitatName);
            this.isolation.displayHabitatMembers();
        }

        if(this.enclosure.getNumberOfMonkeysInHabitat() > 0) {
            System.out.println(this.enclosure.habitatName);
            this.enclosure.displayHabitatMembers();
        }

    }


    @Override
    public void printMonkeysNamesInHabitat() {
        String[] isolated = this.isolation.getMembersNames();
        String[] enclosure = this.enclosure.getMembersNames();


        List list = new ArrayList(Arrays.asList(isolated));
        list.addAll(Arrays.asList(enclosure));
        String[] total = (String[]) list.toArray(new String[0]);
        Arrays.sort(total);
        for (String monkey: total) {
            System.out.println(monkey);
        };

    }

    public void provideMedicalAttention (String name) {

        Primate monkey = this.isolation.getPrimate(name);
        if (monkey != null) monkey.setHealth(100);
    }
}
