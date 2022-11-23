package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.utils.Species;

import java.util.Arrays;

public abstract class Housing {

    protected String habitatName;

    public abstract void displayHabitatMembers();

    public abstract int getNumberOfAnimalsInHabitat();

    protected abstract Animal[] getAnimals();

    public abstract boolean addAnimal(Animal animal);

    protected abstract Animal removeAnimal(Species species, String name);

    protected String[] getMembersNames() {
        return Arrays.stream(this.getAnimals()).map(x -> x.getName()).
                toArray(String[]::new);
    }
}
