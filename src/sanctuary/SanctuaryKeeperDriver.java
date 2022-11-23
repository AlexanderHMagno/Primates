package sanctuary;

import sanctuary.habitat.Enclosure;
import sanctuary.habitat.Isolation;
import sanctuary.habitat.Sanctuary;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public class SanctuaryKeeperDriver {

    public static void main(String[] args) {

        Enclosure oasis = new Enclosure("Oasis - Enclosure");
        Isolation soteria = new Isolation(20, "Soteria - Isolation");
        Sanctuary jungleFriends = new Sanctuary("Jungle Friends Primate Sanctuary", oasis, soteria);

        jungleFriends.addNewAnimal("Alex", Species.Quereza ,Sex.Male, 1.70 , 70.05, 12 , Food.Fruits);
        jungleFriends.addNewAnimal("Sebastian", Species.Saki ,Sex.Male, 1.70 , 70.05, 12 , Food.Eggs);
        jungleFriends.addNewAnimal("Natalia", Species.Quereza ,Sex.Female, 1.60 , 50.05, 6 , Food.Nuts);
        jungleFriends.addNewAnimal("Ana", Species.Tamarin ,Sex.Female, 1.60 , 50.05, 15 , Food.Insects);

//        jungleFriends.printMonkeysInHabitat();
//        jungleFriends.printMonkeysNamesInHabitat();

        jungleFriends.provideMedicalAttention("Natalia");
        jungleFriends.moveAnimalToEnclosure("Natalia");

//        jungleFriends.printMonkeysInHabitat();
//        jungleFriends.printMonkeysNamesInHabitat();

        jungleFriends.moveAnimalToIsolation(Species.Quereza,"Natalia");
        jungleFriends.moveAnimalToIsolation(Species.Quereza,"Naruto");

        jungleFriends.printAnimalsInHabitat();
        jungleFriends.printAnimalsNamesInHabitat();
    }

}
