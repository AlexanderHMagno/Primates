package sanctuary.habitat;

import sanctuary.SanctuaryKeeperDriver;
import sanctuary.model.habitat.Enclosure;
import sanctuary.model.habitat.Isolation;
import sanctuary.model.habitat.Sanctuary;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * This class represents the test for the Sanctuary implementation
 */
public class SanctuaryTest {

    private Sanctuary jungleFriends;
    private Sanctuary fullFriends;
    @org.junit.Before
    public void setUp() {

        jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary");

        Enclosure paradise = new Enclosure("Paradise - Enclosure");
        Isolation rescue = new Isolation(20, "Rescue - Isolation");
        fullFriends = new Sanctuary("Jungle Friends Primate Sanctuary", paradise, rescue);

        SanctuaryKeeperDriver.addAdditionalAnimals(fullFriends, "monkeys.txt");

    }

    /**
     * Verify adding a new animal is valid
     */
    @org.junit.Test
    public void testAddNewAnimal() {
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
        assertEquals(1, jungleFriends.getNumberOfAnimals('t'));

        //As this location is full we need to move one to enclosure
        fullFriends.provideMedicalAttention("Shapito");
        fullFriends.moveAnimalToEnclosure("Shapito");
        fullFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
        assertEquals(21, fullFriends.getNumberOfAnimals('t'));
    }

    /**
     * Test duplicate name in isolation
     */
    @org.junit.Test (expected = IllegalStateException.class)
    public void testIllegalDuplicateNameIsolation() {
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
        jungleFriends.addNewAnimal("alEx", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);

    }

    /**
     * Test duplicate name in Enclosure
     */
    @org.junit.Test (expected = IllegalStateException.class)
    public void testIllegalDuplicateNameEnclosure() {
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);

        //We move the animal to enclosure
        jungleFriends.provideMedicalAttention("Alex");
        jungleFriends.moveAnimalToEnclosure("Alex");

        //Then we try to add a new animal with the same name
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
    }

    /**
     * Test invalid information to create animal
     * No name
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testIllegalNewAnimal() {
        jungleFriends.addNewAnimal("", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
    }

    /**
     * Test invalid information to create animal size
     * negative age
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testIllegalNewAnimalSize() {
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, -20 , 70.05, 12 , Food.Fruits);
    }

    /**
     * Testing overpopulation state, when the isolation is full and can not receive more animals
     */
    @org.junit.Test (expected = IllegalStateException.class)
    public void testOverPopulationIsolation() {
        fullFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 20 , 70.05, 12 , Food.Fruits);
    }


    /**
     * Test an animal can be moved to an enclosure from the isolation area
     */
    @org.junit.Test
    public void moveAnimalToEnclosure() {
        //As this location is full we need to move one to enclosure
        fullFriends.provideMedicalAttention("Shapito");
        fullFriends.moveAnimalToEnclosure("Shapito");
        assertEquals(1, fullFriends.getNumberOfAnimals('e'));
    }

    /**
     * Testing No animal with name in isolation
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testMoveToEnclosureNoAnimal() {
        fullFriends.provideMedicalAttention("Felipe");
        fullFriends.moveAnimalToEnclosure("Felipe");
    }

    /**
     * Testing unhealthy animal to enclosure
     */
    @org.junit.Test (expected = IllegalStateException.class)
    public void testMoveToEnclosureUnhealthyAnimal() {
        fullFriends.moveAnimalToEnclosure("Lolocu");
    }

    /**
     * Test an animal can be moved to an isolation area from the enclosure area
     */
    @org.junit.Test
    public void moveAnimalToIsolation() {
        //As this location is full we need to move one to enclosure
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 20 , 70.05, 12 , Food.Fruits);
        assertEquals(1, jungleFriends.getNumberOfAnimals('i'));

        jungleFriends.provideMedicalAttention("Alex");
        jungleFriends.moveAnimalToEnclosure("Alex");

        //Move to Enclosure remove from isolation
        assertEquals(1, jungleFriends.getNumberOfAnimals('e'));
        assertEquals(0, jungleFriends.getNumberOfAnimals('i'));

        //move back to isolation
        jungleFriends.moveAnimalToIsolation(Species.Quereza, "Alex");
        assertEquals(1, jungleFriends.getNumberOfAnimals('i'));
    }

    /**
     * Testing No animal with name in Enclosure
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testMoveToIsolationNoAnimal() {
        fullFriends.provideMedicalAttention("Felipe");
        fullFriends.moveAnimalToIsolation(Species.Howler,"Felipe");
    }

    /**
     * Test the animal's names are sorted in a correct alphabetical order.
     */
    @org.junit.Test
    public void testGetAnimalsNamesInHabitat() {

        String[] expected ={"Albert", "Andres", "Bulma", "Einstein", "Eureka", "Francis", "Goku", "Jaki",
                "Karen", "Lino", "Lolocu", "Magali", "Malandro", "Naruto", "Navdeep", "Romero", "Romulo",
                "Rosita", "Sebastian", "Shapito"};
        ArrayList<String> actual = fullFriends.getAnimalsNamesInHabitat();
        for (int i = 0; i < expected.length; i++) {
            assertEquals(expected[i],actual.get(i));
        }
    }

    /**
     * Test the health of your monkey is updated after health attention is provided
     */
    @org.junit.Test
    public void provideMedicalAttention() {

        jungleFriends.addNewAnimal("alex", Species.Quereza , Sex.Male,
                        170 , 70.05, 12 , Food.Fruits);

        jungleFriends.provideMedicalAttention("alex");

        String expected = "Animal{\n" +
                "name=Alex,\n" +
                "species=Quereza,\n" +
                "sex=Male,\n" +
                "size=170.0,\n" +
                "weight=70.05,\n" +
                "age=12,\n" +
                "food=Fruits,\n" +
                "health=100,\n" +
                "home=Soteria - Isolation\n" +
                "}";

        assertEquals(expected, jungleFriends.getAnimalBio(Species.Quereza,"Alex",'i'));
    }

    /**
     * Test the get Animals is returning the correct information about your sanctuary
     */
    @org.junit.Test
    public void testGetAnimalsInHabitat() {

        fullFriends.moveForceToEnclosure();
        fullFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 1.70 , 70.05, 12 , Food.Fruits);
        fullFriends.addNewAnimal("Nata", Species.Quereza ,Sex.Female, 1.65 , 50.05, 12 , Food.Nuts);
        fullFriends.addNewAnimal("Luka", Species.Tamarin ,Sex.Male, 1.95 , 80.05, 10 , Food.Insects);

        String expected = "Welcome to Jungle Friends Primate Sanctuary\n" +
                "This is the data information: \n" +
                "\n" +
                "Rescue - Isolation\n" +
                "Animals in Habitat:3\n" +
                "Name: Alex, Sex:Male, Favorite food: Fruits, Species: Quereza\n" +
                "Name: Luka, Sex:Male, Favorite food: Insects, Species: Tamarin\n" +
                "Name: Nata, Sex:Female, Favorite food: Nuts, Species: Quereza\n" +
                "\n" +
                "Paradise - Enclosure\n" +
                "Animals in Habitat:20\n" +
                "Species:Drill\n" +
                "Name: Malandro Sex:Male Favorite food: Eggs, Species: Drill\n" +
                "Species:Howler\n" +
                "Name: Francis Sex:Female Favorite food: TreeSap, Species: Howler\n" +
                "Name: Sebastian Sex:Male Favorite food: Insects, Species: Howler\n" +
                "Species:Mangabey\n" +
                "Name: Albert Sex:Male Favorite food: Leaves, Species: Mangabey\n" +
                "Name: Romero Sex:Male Favorite food: Leaves, Species: Mangabey\n" +
                "Species:Quereza\n" +
                "Name: Lino Sex:Male Favorite food: Eggs, Species: Quereza\n" +
                "Name: Lolocu Sex:Female Favorite food: Fruits, Species: Quereza\n" +
                "Name: Magali Sex:Female Favorite food: Fruits, Species: Quereza\n" +
                "Name: Navdeep Sex:Male Favorite food: Fruits, Species: Quereza\n" +
                "Name: Rosita Sex:Female Favorite food: TreeSap, Species: Quereza\n" +
                "Name: Shapito Sex:Male Favorite food: Fruits, Species: Quereza\n" +
                "Species:Saki\n" +
                "Name: Einstein Sex:Male Favorite food: Nuts, Species: Saki\n" +
                "Name: Eureka Sex:Female Favorite food: Nuts, Species: Saki\n" +
                "Species:Spider\n" +
                "Name: Bulma Sex:Female Favorite food: Seeds, Species: Spider\n" +
                "Name: Goku Sex:Male Favorite food: Seeds, Species: Spider\n" +
                "Name: Jaki Sex:Male Favorite food: Eggs, Species: Spider\n" +
                "Species:Squirrel\n" +
                "Name: Karen Sex:Female Favorite food: TreeSap, Species: Squirrel\n" +
                "Name: Naruto Sex:Male Favorite food: TreeSap, Species: Squirrel\n" +
                "Species:Tamarin\n" +
                "Name: Andres Sex:Male Favorite food: Fruits, Species: Tamarin\n" +
                "Name: Romulo Sex:Male Favorite food: Fruits, Species: Tamarin\n";
        assertEquals(expected, fullFriends.getAnimalsInHabitat());

    }
}