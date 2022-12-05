package sanctuary.controller;

import sanctuary.SanctuaryKeeperDriver;
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

        boolean created = false;

        this.model.addNewAnimal(name,species,sex,size,weight,age,food);

        return created;
    }

    @Override
    public void transferAnimalToEnclosure(String animal) throws IllegalStateException, IllegalArgumentException {
        this.model.moveAnimalToEnclosure(animal);
    }

    @Override
    public void transferAnimalToIsolation(Species species, String animal) throws IllegalArgumentException {
        this.model.moveAnimalToIsolation(species,animal);
    }

    @Override
    public String getFavoriteFood(Species valueOf, String selectedValue) {
        return this.model.getFavoriteFood(valueOf, selectedValue);
    }

    @Override
    public String[] searchAnimalByName(String searched) {
        return this.model.getAnimalBioByName(searched);
    }

    @Override
    public void provideMedicalAttention(String animal) {
        this.model.provideMedicalAttention(animal);
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
    public ArrayList<String> displayAllAnimalsNames(char location) {
        return this.model.getAnimalsNamesInHabitat(location);
    }


    @Override
    public void closeProgram() {

    }

    @Override
    public void go() {

        this.view.addFeatures(this);
        this.view.makeVisible();
        this.updateDashboard();
    }

    @Override
    public void updateIsolation() {
        this.view.updateIsolationArea();
    }

    @Override
    public void updateEnclosure() {
        this.view.updateEnclosureArea();
    }

    @Override
    public String getAnimalBio(Species species, String text, char location) {
        return this.model.getAnimalBio(species,text,location);
    }

    @Override
    public String[] displayAnimalsInEnclosureGroup(Species species) {
        return this.model.displayAnimalsInEnclosureGroup(species) ;
    }

    @Override
    public void updateDashboard() {
//        SanctuaryKeeperDriver.addAdditionalAnimals(model, "monkeys.txt");
        this.view.setDashboardInfo(model.getAnimalsInHabitat());
    }
}
