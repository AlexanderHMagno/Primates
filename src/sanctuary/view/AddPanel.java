package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * This class represents an Add Pane. It is used to create a Panel in the habitat
 */
public class AddPanel extends JFrame {

    private JTextField name;
    private JComboBox<String> sexC , speciesC , foodC;
    private JSpinner sizeC, weightC, ageC;
    private JTextArea areaResponse, area1;
    private final SanctuaryFeatures features;
    private final Component component;


    /**
     * Constructor that accepts a features class (actions) and a global component to append child components
     * @param features Controller the brain of this application
     * @param component Global component to append the add panel
     */
    public AddPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    /**
     * Method that will create and rendering the add Panel folder
     * @return The components of the add Folder glue to a unique JPanel
     */
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
        speciesC = createComboBox(Utilities.getEnumNames(Species.class), 250);
        panel.add(speciesC);
        panel.add(createLabel("Species", 250));

        //sex
        sexC= createComboBox(Utilities.getEnumNames(Sex.class), 300);
        panel.add(sexC);
        panel.add(createLabel("Sex", 300));

        //Food
        foodC= createComboBox(Utilities.getEnumNames(Food.class), 350);
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

    /**
     * Create action, that will take inputs and send a request to create a new animal
     */
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

    /**
     * If the user wants to remove the form and start from scratch
     */
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

    /**
     * Create a JLabel of the same size and on the same horizontal position.
     * @param text to display
     * @param y vertical position in the container
     * @return an instance of a JLabel
     */
    private JLabel createLabel(String text, int y) {
        JLabel label = new JLabel(text + ": ");
        label.setSize(300, 30);
        label.setLocation(200, y);
        return label;
    }

    /**
     * Create a Combobox of the same size and at the same horizontal position
     * @param list Options to display
     * @param y Vertical position in the container
     * @return An instance of a comboBox
     */
    private JComboBox<String> createComboBox(String[] list, int y) {
        JComboBox<String> cb = new JComboBox<>(list);
        cb.setSize(100, 30);
        cb.setLocation(250, y);
        return cb;
    }

    /**
     * create JSpinner of the same size and at the same horizontal position
     * @param SNM Filter with the minimum, max, initial value and steps.
     * @param y Vertical position on this container
     * @return formatted JSpinner
     */
    private JSpinner createJSpinner(SpinnerNumberModel SNM, int y) {
        JSpinner spin = new JSpinner(SNM);
        spin.setSize(100, 30);
        spin.setLocation(250, y);
        return spin;
    }
}