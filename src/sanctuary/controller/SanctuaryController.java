package sanctuary.controller;

import sanctuary.model.habitat.Habitat;
import sanctuary.model.habitat.animals.Animal;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import sanctuary.view.SanctuaryView;

import java.util.ArrayList;

public class SanctuaryController implements SanctuaryFeatures{

    private Habitat model;
    private SanctuaryView view;
    public SanctuaryController(Habitat model, SanctuaryView view) {
        this.model = model;
        this.view = view;
    }


    @Override
    public boolean addAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food) throws IllegalStateException, IllegalArgumentException {
        return false;
    }

    @Override
    public boolean transferAnimalToEnclosure(String animal) throws IllegalStateException, IllegalArgumentException {
        return false;
    }

    @Override
    public boolean transferAnimalToIsolation(String animal) throws IllegalArgumentException {
        return false;
    }

    @Override
    public void provideMedicalAttention(String animal) {

    }

    @Override
    public ArrayList<Animal> displayAnimalsInEnclosure() {
        return null;
    }

    @Override
    public ArrayList<Animal> displayAnimalsInIsolation() {
        return null;
    }

    @Override
    public ArrayList<String> displayAllAnimalsNames() {
        return null;
    }

    @Override
    public void closeProgram() {

    }

    @Override
    public void go() {

        this.view.makeVisible();
        this.view.setDashboardInfo(model.getAnimalsInHabitat());
    }
}
