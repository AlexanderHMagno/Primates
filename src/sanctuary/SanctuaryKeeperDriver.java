package sanctuary;

import sanctuary.housing.Enclosure;
import sanctuary.housing.Isolation;
import sanctuary.housing.Sanctuary;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public class SanctuaryKeeperDriver {

    public static void main(String[] args) {

        Enclosure oasis = new Enclosure("Oasis - Enclosure");
        Isolation soteria = new Isolation(20, "Soteria - Isolation");
        Sanctuary jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary", oasis, soteria);

        jungleFriends.addNewMonkey("Alex", Species.Quereza ,Sex.Male, 1.70 , 70.05, 12 , Food.Fruits);
        jungleFriends.addNewMonkey("Sebastian", Species.Saki ,Sex.Male, 1.70 , 70.05, 12 , Food.Eggs);
        jungleFriends.addNewMonkey("Natalia", Species.Quereza ,Sex.Female, 1.60 , 50.05, 6 , Food.Nuts);
        jungleFriends.addNewMonkey("Ana", Species.Tamarin ,Sex.Female, 1.60 , 50.05, 15 , Food.Insects);

//        jungleFriends.printMonkeysInHabitat();
//        jungleFriends.printMonkeysNamesInHabitat();

        jungleFriends.provideMedicalAttention("Natalia");
        jungleFriends.moveMonkeyToEnclosure("Natalia");

        jungleFriends.printMonkeysInHabitat();
        jungleFriends.printMonkeysNamesInHabitat();

    }

}
