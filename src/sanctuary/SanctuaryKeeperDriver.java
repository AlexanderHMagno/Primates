package sanctuary;

import sanctuary.controller.SanctuaryController;
import sanctuary.controller.SanctuaryFeatures;
import sanctuary.model.habitat.Habitat;
import sanctuary.model.habitat.Sanctuary;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import sanctuary.view.SanctuaryAnimalView;
import sanctuary.view.SanctuaryView;

import java.io.*;
import java.util.Scanner;


/**
 * This class represents the driver of this implementation.
 */
public class SanctuaryKeeperDriver {

    public static void main(String[] args) {

        Habitat model = new Sanctuary("Jungle Friends Primate Sanctuary");
        SanctuaryKeeperDriver.addAdditionalAnimals(model, "monkeys.txt");
        SanctuaryView view = new SanctuaryAnimalView();
        SanctuaryFeatures controller = new SanctuaryController(model,view);
        controller.go();


    }

    /**
     * Create additional animals based on the provided file
     * @param sanctuary The habitat that you want to add these animals
     * @param group the name of the file in utils with the list of animals to add
     */
    public static void addAdditionalAnimals(Habitat sanctuary, String group) {

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
