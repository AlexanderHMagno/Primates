package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AddPanel extends JFrame {

    private JTextField name;
    private JComboBox<String> sexC , speciesC , foodC;
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

        //Title
        panel.add(panel.add(Utilities.createTitle("Add new Monkey")));

        //name
        name = new JTextField("",15);
        name.setSize(300, 30);
        name.setLocation(250, 200);
        panel.add(name);
        panel.add(createLabel("Name", 200));

        //Species
        speciesC = createComboBox(Utilities.getNames(Species.class), 250);
        panel.add(speciesC);
        panel.add(createLabel("Species", 250));

        //sex
        sexC= createComboBox(Utilities.getNames(Sex.class), 300);
        panel.add(sexC);
        panel.add(createLabel("Sex", 300));

        //Food
        foodC= createComboBox(Utilities.getNames(Food.class), 350);
        panel.add(foodC);
        panel.add(createLabel("Food", 350));

        //size
        sizeC = createJSpinner(new SpinnerNumberModel(1,1,1000, 0.1),400);
        panel.add(sizeC);
        panel.add(createLabel("Size", 400));

        //Weight
        weightC = createJSpinner(new SpinnerNumberModel(1,1,1000, 0.1),450);
        panel.add(weightC);
        panel.add(createLabel("Weight", 450));

        //Age
        ageC = createJSpinner(new SpinnerNumberModel(1,1,100, 1),500);
        panel.add(ageC);
        panel.add(createLabel("Age", 500));

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
                    Species.valueOf(Objects.requireNonNull(speciesC.getSelectedItem()).toString()),
                    Sex.valueOf(Objects.requireNonNull(sexC.getSelectedItem()).toString()),
                    (double) sizeC.getValue(),
                    (double) weightC.getValue(),
                    (int) ageC.getValue(),
                    Food.valueOf(Objects.requireNonNull(foodC.getSelectedItem()).toString())
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
        sizeC.setValue(1.00);
        weightC.setValue(1.00);
        ageC.setValue(1);
        foodC.setSelectedIndex(0);

        areaResponse.setText("");
        area1.setText("");

    }

    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text + ": ");
        label.setSize(300, 30);
        label.setLocation(200, y);
        return label;
    }

    private JComboBox<String> createComboBox(String[] list, int y) {
        JComboBox<String> cb = new JComboBox<>(list);
        cb.setSize(100, 30);
        cb.setLocation(250, y);
        return cb;
    }

    private JSpinner createJSpinner(SpinnerNumberModel SNM, int y) {
        JSpinner spin = new JSpinner(SNM);
        spin.setSize(100, 30);
        spin.setLocation(250, y);
        return spin;
    }

}
