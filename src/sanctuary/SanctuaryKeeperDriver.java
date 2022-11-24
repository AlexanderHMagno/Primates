package sanctuary;

import sanctuary.habitat.Enclosure;
import sanctuary.habitat.Isolation;
import sanctuary.habitat.Sanctuary;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import java.io.*;
import java.util.Scanner;


/**
 * This class represents the driver of this implementation.
 */
public class SanctuaryKeeperDriver {

    public static void main(String[] args) {

        Enclosure oasis = new Enclosure("Oasis - Enclosure");
        Isolation soteria = new Isolation(20, "Soteria - Isolation");

        //A sanctuary is composed of two types of housing.
        Sanctuary jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary", oasis, soteria);

        //Adding Other 20 Primates (helper method)
        SanctuaryKeeperDriver.addAdditionalAnimals(jungleFriends, "monkeys.txt");

        //Adding a monkey manually - if You add this one right now it won't be added, there are already 20 monkeys.
        //And Animals must receive valid name, size, weight, age otherwise exception will be thrown
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 1.70 , 70.05, 12 , Food.Fruits);

        //Forcing monkeys to move to enclosure (test method)
        // This will cure and move every monkey from isolation to enclosure
        jungleFriends.moveForceToEnclosure();

        //Isolation has room you can add more - create 3 more monkeys manually
        //Note: Name must be different for each animal
        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 1.70 , 70.05, 12 , Food.Fruits);
        jungleFriends.addNewAnimal("Nata", Species.Quereza ,Sex.Female, 1.65 , 50.05, 12 , Food.Nuts);
        jungleFriends.addNewAnimal("Luka", Species.Tamarin ,Sex.Male, 1.95 , 80.05, 10 , Food.Insects);

        //This is the manual way to move animal to enclosure
//      jungleFriends.moveAnimalToEnclosure("Nata"); // If you try to move an unhealthy animal to enclosure Exception.
        jungleFriends.provideMedicalAttention("Nata");
        jungleFriends.moveAnimalToEnclosure("Nata");

        //You can also move back Animals to Isolation
        jungleFriends.moveAnimalToIsolation(Species.Quereza,"Shapito");

        //Display Animals in habitat (Isolation + Enclosure)
        jungleFriends.printAnimalsInHabitat();
        //Display the names of the animals in the habitat
        jungleFriends.printAnimalsNamesInHabitat();
    }

    /**
     * Create additional animals based on the provided file
     * @param sanctuary The habitat that you want to add these animals
     * @param group the name of the file in utils with the list of animals to add
     */
    public static void addAdditionalAnimals(Sanctuary sanctuary, String group) {

        try {
            File file = new File("src/sanctuary/utils/" +  group);
            Scanner scan = new Scanner(file);

            while (scan.hasNextLine())   {
                String[] tokens = scan.nextLine().split(" ");
                sanctuary.addNewAnimal(
                        tokens[0],
                        Species.valueOf(tokens[1]),
                        Sex.valueOf(tokens[2]),
                        Double.parseDouble(tokens[3]),
                        Double.parseDouble(tokens[4]),
                        Integer.parseInt(tokens[5]),
                        Food.valueOf(tokens[6])
                );
            }
            scan.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
