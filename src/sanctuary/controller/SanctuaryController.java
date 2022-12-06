package sanctuary.controller;

import sanctuary.model.habitat.Habitat;
import sanctuary.model.habitat.animals.Animal;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;
import sanctuary.view.SanctuaryView;
import sanctuary.view.Utilities;

import java.util.ArrayList;

public class SanctuaryController implements SanctuaryFeatures{

    private final Habitat model;
    private final SanctuaryView view;
    public SanctuaryController(Habitat model, SanctuaryView view) {
        this.model = model;
        this.view = view;

    }


    @Override
    public void addAnimal(String name, Species species, Sex sex, double size, double weight, int age, Food food) throws IllegalStateException, IllegalArgumentException {
        this.model.addNewAnimal(name,species,sex,size,weight,age,food);
    }

    @Override
    public void transferAnimalToEnclosure(String animal) throws IllegalStateException, IllegalArgumentException {
        if(animal.trim().length() == 0) throw new IllegalArgumentException("No Animal detected");
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
    public String sanctuaryName() {
        return this.model.getName();
    }

    @Override
    public int animalInEnclosure(Species valueOf) {
        return this.model.getNumberOfAnimalsEnclosure(valueOf);
    }

    @Override
    public void massiveLoader() throws IllegalStateException {
        Utilities.addAdditionalAnimals(this.model, "monkeys.txt");
    }

    @Override
    public void massiveHealer() {
        this.model.moveForceToEnclosure();
        this.view.updateIsolationArea();
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
        this.view.packView(this);
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
