package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import java.awt.*;

public class AddPanel extends JFrame {

    private JTextField name;
    private JComboBox sexC, speciesC, foodC;
    private JSpinner sizeC, weightC, ageC;
    private JTextArea areaResponse, area1;
    private final SanctuaryFeatures features;
    private final Component component;


    public AddPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createAddFolder(){

        JPanel container = new JPanel();
        JTextArea panel = Utilities.createContainer();

        JLabel nameL, sexL, speciesL, sizeL, weightL, ageL, foodL, title;


        //Title
        panel.add(panel.add(Utilities.createTitle("Add new Monkey")));

        //name
        name = new JTextField("",15);
        nameL = new JLabel("Name:");
        nameL.setSize(300, 30);
        name.setSize(300, 30);
        name.setLocation(250, 200);
        nameL.setLocation(200, 200);
        panel.add(nameL);
        panel.add(name);

        //Species

        String[] speciesList = Utilities.getNames(Species.class);
        speciesC= new JComboBox(speciesList);
        speciesL = new JLabel("Species:");
        speciesL.setSize(300, 30);
        speciesC.setSize(100, 30);
        speciesC.setLocation(250, 250);
        speciesL.setLocation(200, 250);
        panel.add(speciesL);
        panel.add(speciesC);

        //sex
        String[] sexList = Utilities.getNames(Sex.class);
        sexC= new JComboBox(sexList);
        sexL = new JLabel("Sex:");
        sexL.setSize(300, 30);
        sexC.setSize(100, 30);
        sexC.setLocation(250, 300);
        sexL.setLocation(200, 300);
        panel.add(sexL);
        panel.add(sexC);

        //Food
        String foodList[] = Utilities.getNames(Food.class);
        foodC= new JComboBox(foodList);
        foodL = new JLabel("Favorite Food:");
        foodL.setSize(300, 30);
        foodC.setSize(100, 30);
        foodC.setLocation(300, 350);
        foodL.setLocation(200, 350);
        panel.add(foodL);
        panel.add(foodC);

        //size
        SpinnerModel sizeFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        sizeC = new JSpinner(sizeFormat);
        sizeL = new JLabel("Size: ");
        sizeL.setSize(300, 30);
        sizeC.setSize(100, 30);
        sizeC.setLocation(250, 400);
        sizeL.setLocation(200, 400);
        panel.add(sizeL);
        panel.add(sizeC);

        //Weight
        SpinnerModel weightFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        weightC = new JSpinner(weightFormat);
        weightL = new JLabel("Weight: ");
        weightL.setSize(300, 30);
        weightC.setSize(100, 30);
        weightC.setLocation(250, 450);
        weightL.setLocation(200, 450);
        panel.add(weightL);
        panel.add(weightC);

        //Age
        SpinnerModel ageFormat = new SpinnerNumberModel(0, 0, 100, 1);
        ageC = new JSpinner(ageFormat);
        ageL = new JLabel("Age: ");
        ageL.setSize(300, 30);
        ageC.setSize(100, 30);
        ageC.setLocation(250, 500);
        ageL.setLocation(200, 500);
        panel.add(ageL);
        panel.add(ageC);

        area1 = Utilities.bioPanel();
        panel.add(area1);

        areaResponse = Utilities.bioPanel();
        areaResponse.setSize(300, 75);
        areaResponse.setLineWrap(true);
        areaResponse.setWrapStyleWord(true);
        areaResponse.setLocation(800, 450);

        panel.add(areaResponse);

        //Add Buttons
        JButton createButton = Utilities.addButton("Add Animal", 200, 550);
        createButton.addActionListener(e -> this.createAction());
        panel.add(createButton);

        JButton resetButton = Utilities.addButton("Reset", 400,550);
        resetButton.addActionListener(e -> this.resetAction());
        panel.add(resetButton);

        container.add(panel);
        return container;

    }


    private void createAction() {
        try{
            String nameF = name.getText().trim().length() > 0 ? name.getText() : " ";
            features.addAnimal(
                    nameF,
                    Species.valueOf(speciesC.getSelectedItem().toString()),
                    Sex.valueOf(sexC.getSelectedItem().toString()),
                    (double) sizeC.getValue(),
                    (double) weightC.getValue(),
                    (int) ageC.getValue(),
                    Food.valueOf(foodC.getSelectedItem().toString())
            );

            area1.setText(features.getAnimalBio(Species.valueOf(speciesC.getSelectedItem().toString()),name.getText(),'i'));
            name.setText("");
            areaResponse.setText("Monkey has been accepted");
        } catch (IllegalArgumentException | IllegalStateException userError) {
            areaResponse.setText(userError.getMessage());
        }

    }

    private void resetAction() {

        name.setText("");
        speciesC.setSelectedIndex(0);
        sexC.setSelectedIndex(0);
        sizeC.setValue(0.00);
        weightC.setValue(0.00);
        ageC.setValue(0);
        foodC.setSelectedIndex(0);

        areaResponse.setText("");
        area1.setText("");

    }

}
