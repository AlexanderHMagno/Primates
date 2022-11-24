package sanctuary.habitat;

import sanctuary.animals.Animal;
import sanctuary.animals.AnimalCreator;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import static org.junit.Assert.*;

public class SanctuaryTest {

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.Test
    public void addNewAnimal() {
    }

    @org.junit.Test
    public void moveAnimalToEnclosure() {
    }

    @org.junit.Test
    public void moveAnimalToIsolation() {
    }

    @org.junit.Test
    public void printAnimalsInHabitat() {
    }

    @org.junit.Test
    public void printAnimalsNamesInHabitat() {
    }

    @org.junit.Test
    public void provideMedicalAttention() {

        Animal alex = AnimalCreator
                .createAnimal("alex", Species.Quereza , Sex.Male,
                        170 , 70.05, 12 , Food.Fruits);

    }
}