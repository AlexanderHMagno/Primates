package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * This class represents a Search Panel. It is used to create a Panel in the habitat
 */
public class SearchPanel {

    private JTextField input;

    private final SanctuaryFeatures features;
    private final Component component;
    private boolean loaded = false;


    /**
     * Constructor that accepts a features class (actions) and a global component to append child components
     * @param features Controller the brain of this application
     * @param component Global component to append the Search panel
     */
    public SearchPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    /**
     * Method that will create and render the search Panel folder
     * @return The components of the Search Area glue to a unique JPanel
     */
    public JPanel addSearchArea() {
        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        //input textField
        input = new JTextField(15);
        buttonPanel.add(input);

        //buttons
        JButton commandButton = new JButton("Search");
        commandButton.addActionListener(r -> runSearch());
        buttonPanel.add(commandButton);

        //quit button
        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> System.exit(0));
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    /**
     * Action when the user click on search
     */
    private void runSearch() {
        String searched = input.getText().trim();
        input.setText("");

        if (searched.length() > 0) {
            try{
                //Code To add multiple monkeys
                String loaderGroup = "loader123";
                String doctorGroup = "doctor123";
                if (searched.equals(loaderGroup) && !loaded) {
                    features.massiveLoader();
                    Utilities.showInfoMessage(
                            "Monkeys Loaded",
                            "Another habitat closed, and the monkeys were transferred to our location",
                            this.component
                    );
                    features.updateIsolation();
                    loaded = !loaded;
                    return;
                }  else if (searched.equals(doctorGroup)){
                    features.massiveHealer();
                    Utilities.showInfoMessage(
                            "Monkeys cured",
                            "A Magical doctor has provided medical attention and moved the animals to their habitats",
                            this.component
                    );
                    return;
                }

                String[] location = features.searchAnimalByName(searched);
                Utilities.showInfoMessage(
                        "Monkey Location" ,
                        "Location: " + location[0] + "\n\n" + location[1]
                        ,this.component);

            } catch (IllegalArgumentException | IllegalStateException d ) {
                Utilities.showErrorMessage(d.getMessage(), this.component);
            }
        }
    }
}
