package sanctuary.animals;

import sanctuary.habitat.Housing;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public interface Animal {

    String getName();
    Food getFood();
    Sex getSex();
    Species getSpecies();

    void setHome(Housing home);
    void setHealth(int health);
    boolean needsMedicalAttention ();

}
