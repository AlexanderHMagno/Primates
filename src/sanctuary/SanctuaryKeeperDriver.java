package sanctuary;

import sanctuary.controller.SanctuaryController;
import sanctuary.controller.SanctuaryFeatures;
import sanctuary.model.habitat.Habitat;
import sanctuary.model.habitat.Sanctuary;
import sanctuary.view.SanctuaryAnimalView;
import sanctuary.view.SanctuaryView;


/**
 * This class represents the driver of this implementation.
 */
public class SanctuaryKeeperDriver {
    public static void main(String[] args) {

        Habitat model = new Sanctuary("Jungle Friends Primate Sanctuary");

        SanctuaryView view = new SanctuaryAnimalView();
        SanctuaryFeatures controller = new SanctuaryController(model,view);
        controller.go();
    }
}
