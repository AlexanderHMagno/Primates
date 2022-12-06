package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SearchPanel {

    private JPanel buttonPanel;
    private JTextField input;
    private JButton commandButton, quitButton;

    private SanctuaryFeatures features;
    private Component component;
    private final String LoaderGroup = "loader123";
    private final String doctorGroup = "doctor123";
    private boolean loaded = false;



    public SearchPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }


    public JPanel addSearchArea() {
        //button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        //input textField
        input = new JTextField(15);
        buttonPanel.add(input);

        //buttons
        commandButton = new JButton("Search");
        commandButton.addActionListener(r -> runSearch());
        buttonPanel.add(commandButton);

        //quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> {System.exit(0);});
        buttonPanel.add(quitButton);

        return buttonPanel;
    }

    private void runSearch() {
        String searched = input.getText().trim();
        input.setText("");

        if (searched.length() > 0) {
            try{
                //Code To add multiple monkeys
                if (searched.equals(LoaderGroup) && !loaded) {
                    features.massiveLoader();
                    Utilities.showInfoMessage(
                            "Monkeys Loaded",
                            "Another habitat closed, and the monkeys were transferred to our location",
                            this.component
                    );
                    loaded = !loaded;
                    return;
                }  else if (searched.equals(doctorGroup)){
                    features.massiveHealer();
                    Utilities.showInfoMessage(
                            "Monkeys cured",
                            "A Magic doctor has provided medical attention and moved the animals to their habitats",
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
