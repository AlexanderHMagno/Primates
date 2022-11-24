package sanctuary.habitat;

import sanctuary.SanctuaryKeeperDriver;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SanctuaryTest {

    private Sanctuary jungleFriends;
    private Sanctuary fullFriends;
    @org.junit.Before
    public void setUp() throws Exception {

        Enclosure oasis = new Enclosure("Oasis - Enclosure");
        Isolation soteria = new Isolation(20, "Soteria - Isolation");
        jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary", oasis, soteria);

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
     * Test invalid information to create animal
     */
    @org.junit.Test (expected = IllegalArgumentException.class)
    public void testIllegalNewAnimal() {
        jungleFriends.addNewAnimal("", Species.Quereza ,Sex.Male, 170 , 70.05, 12 , Food.Fruits);
    }

    /**
     * Test invalid information to create animal size
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


    @org.junit.Test
    public void testGetAnimalsInHabitat() {

    }

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
}