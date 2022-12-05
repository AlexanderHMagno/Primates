package sanctuary.view;

import jdk.jshell.execution.Util;
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


    public SearchPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }


    public JPanel addSearchArea() {
        //button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());


        //input textfield
        input = new JTextField(15);
        buttonPanel.add(input);

        //buttons
        commandButton = new JButton("Search");
        commandButton.addActionListener((ActionEvent e) ->
        {
            String searched = input.getText().trim();
            if (searched.length() > 0) {

                try{
                    String[] location = features.searchAnimalByName(searched);
                    Utilities.showInfoMessage("Monkey Location" , "Location: " + location[0] + "\n\n" + location[1]
                            ,this.component);
                    input.setText("");
                } catch (IllegalArgumentException d ) {
                    Utilities.showErrorMessage(d.getMessage(), this.component);
                }
            }
        });
        buttonPanel.add(commandButton);

        //quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> {System.exit(0);});
        buttonPanel.add(quitButton);

        return buttonPanel;

    }
}
