package sanctuary.monkeys;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

public interface Primate {

    boolean needsMedicalAttention ();

    public Species getSpecies();
    public Food getFood();

    public String getName();

    public Sex getSex();

    public void setHealth(int health);

}
