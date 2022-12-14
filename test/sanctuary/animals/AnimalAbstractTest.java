package sanctuary.animals;

import org.junit.Before;
import org.junit.Test;
import sanctuary.model.habitat.animals.Animal;
import sanctuary.model.habitat.animals.AnimalAbstract;
import sanctuary.model.habitat.animals.Monkey;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import static org.junit.Assert.*;

/**
 * Class to test an animal instance
 */
public class AnimalAbstractTest {

    protected Animal bong;
    protected Animal shuta;

    @Before
    public void setUp() {
        bong = new Monkey("bOnG", Species.Howler, Sex.Male, 180, 130.33, 14, Food.Nuts);
        shuta = new Monkey("shutA", Species.Mangabey, Sex.Female, 190, 150.33, 19, Food.TreeSap);
    }

    /**
     * Test Exception when no name is given
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalNoName(){
        new Monkey("", Species.Howler, Sex.Male, 180, 130.33, 14, Food.Nuts );
    }

    /**
     * Test Exception when no valid height is given
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalNoHeight(){
        new Monkey("Alex", Species.Howler, Sex.Male, -20, 130.33, 14, Food.Nuts );
    }

    /**
     * Test Exception when no valid weight is given
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalNoWeight(){
        new Monkey("Alex", Species.Howler, Sex.Male, 120, -130.33, 14, Food.Nuts );
    }

    /**
     * Test Exception when no valid age is given
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAnimalNoAge(){
        new Monkey("Alex", Species.Howler, Sex.Male, 120, 130.33, -12, Food.Nuts );
    }

    /**
     * Test an instance of an Animal, these are basic methods.
     */
    @Test
    public void testConstructorAndBasicMethods() {

        assertEquals("Bong", bong.getName() );
        assertEquals(Species.Howler, bong.getSpecies() );
        assertEquals(Sex.Male, bong.getSex() );
        assertEquals(Food.Nuts, bong.getFood());
        assertEquals(50,bong.getHealth());

        assertEquals("Shuta", shuta.getName() );
        assertEquals(Species.Mangabey, shuta.getSpecies() );
        assertEquals(Sex.Female, shuta.getSex() );
        assertEquals(Food.TreeSap, shuta.getFood());
        assertEquals(50,shuta.getHealth());
    }

    /**
     * Test the health is being updated
     */
    @Test
    public void testSetHealth() {
        bong.setHealth(200);
        shuta.setHealth(80);

        assertEquals(200,bong.getHealth());
        assertEquals(80,shuta.getHealth());
    }

    /**
     * Test trying to update health lower than 0
     */
    @Test(expected = IllegalArgumentException.class)
    public void setHealth() {
        bong.setHealth(-200);
    }

    /**
     * Verify the animal would not need more medical attention after receive
     * the initial one
     */
    @Test
    public void needsMedicalAttention() {

        assertTrue(bong.needsMedicalAttention());
        assertTrue(shuta.needsMedicalAttention());

        // Provide medical attention
        bong.setHealth(200);
        shuta.setHealth(100);

        assertFalse(bong.needsMedicalAttention());
        assertFalse(shuta.needsMedicalAttention());

    }

    /**
     * Test that the name is always Capitalized
     */
    @Test
    public void testFormatName() {
        assertEquals("Alexander", AnimalAbstract.formatName("aLeXanDer"));
    }


    /**
     * Test Illegal argument
     */
    @Test(expected = IllegalArgumentException.class)
    public void testIllegalName() {
        assertEquals("", AnimalAbstract.formatName(""));
    }

    /**
     * Test the toString method
     */
    @Test
    public void testToString() {

        String bongString = "\n" +
                "Name: Bong,\n" +
                "Species: Howler,\n" +
                "Sex: Male,\n" +
                "Size: 180.0,\n" +
                "Weight: 130.33,\n" +
                "Age: 14,\n" +
                "Food: Nuts,\n" +
                "Health: 50,\n" +
                "Home: Wild animal\n";

        assertEquals(bongString, bong.toString());
    }
}