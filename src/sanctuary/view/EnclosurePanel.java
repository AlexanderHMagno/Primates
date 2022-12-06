package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EnclosurePanel extends JFrame {

    private  JTextArea enclosureBio;
    private JLabel enclosureBioTitle;
    private JPanel enclosureArea;
    private JList<String> list;
    private final DefaultListModel<String> membersList = new DefaultListModel<>();
    private final SanctuaryFeatures features;
    private final Component component;


    public EnclosurePanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createEnclosureFolder() {

        JPanel container = new JPanel();
        JTextArea panel = Utilities.createContainer();

        //Title
        panel.add(panel.add(Utilities.createTitle("Enclosure Area")));

        //Habitats per specie
        enclosureArea = Utilities.createGridRooms();
        panel.add(enclosureArea);

        //Add Bio Panel
        enclosureBio = Utilities.bioPanel();
        enclosureBioTitle = new JLabel("Members");
        enclosureBioTitle.setSize(200, 30);
        enclosureBioTitle.setLocation(800, 420);
        enclosureBioTitle.setFont(new Font("Arial", Font.BOLD, 15));
        panel.add(enclosureBio);
        panel.add(enclosureBioTitle);

        //Add Groups of species
        for (String group: Utilities.getNames(Species.class)) {
            this.createEnvironments(group);
        }

        //Add List to select
        list = new JList<>(membersList);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addListSelectionListener(e-> this.changeList());
        list.setSize(300,100);
        list.setBorder(new EmptyBorder(10, 10, 10, 10));
        list.setLocation(800,450);
        panel.add(list);

        //Add Buttons
        JButton moveButton = Utilities.addButton("Move to Isolation", 200, 550);
        moveButton.addActionListener(e -> this.moveAction());
        panel.add(moveButton);

        JButton feedButton = Utilities.addButton("Feed", 400,550);
        feedButton.addActionListener(e -> this.feedAction());
        panel.add(feedButton);

        //Add everything to the container
        container.add(panel);

        //Design location
        this.paintEnclosures();

        return container;
    }

    private void createEnvironments(String group) {
        JButton subGroup = new JButton();
        subGroup.setName(group);
        subGroup.setSize(400,500);
        subGroup.setSize(new Dimension(400,400));
        subGroup.setMaximumSize(new Dimension(400, 400));
        subGroup.setText(group);

        subGroup.addActionListener(x -> {
            enclosureBioTitle.setText(group);
            updateAnimalsInGroup(group);
            enclosureBio.setText("");
        });
        enclosureArea.add(subGroup);
    }

    public void updateEnclosureArea() {

        paintEnclosures();
        enclosureBioTitle.setText("Members");
        enclosureBio.setText("");
        membersList.removeAllElements();
    }

    private void updateAnimalsInGroup(String group) {
        membersList.removeAllElements();
        for (String en :features.displayAnimalsInEnclosureGroup(Species.valueOf(group)) ) {
            membersList.addElement(en);
        }
    }

    private void paintEnclosures() {

        for (Component c : enclosureArea.getComponents()) {
            String group = c.getName();
            JButton cButton = (JButton) c;

            int numOfAnimals = features.animalInEnclosure(Species.valueOf(group));
            cButton.setText(group + " ("+ numOfAnimals+")");

            if (numOfAnimals != 0) {cButton.setForeground(new Color(206, 50, 50));
            } else {cButton.setForeground(new Color(92, 113, 131));}
        }
    }

    private void feedAction() {
        try {
            Utilities.showInfoMessage("Food Food", "Ook-ook! \n" +
                    features.getFavoriteFood(Species.valueOf(enclosureBioTitle.getText()), list.getSelectedValue())
                    + "\nEeek-aak-eek!", this.component
            );
        } catch (NullPointerException| IllegalArgumentException n) {
            Utilities.showErrorMessage("No Animal Detected", this.component);
        }
    }

    private void moveAction() {

        try {
            features.transferAnimalToIsolation(Species.valueOf(enclosureBioTitle.getText()), list.getSelectedValue() );
            updateAnimalsInGroup(enclosureBioTitle.getText());
            enclosureBio.setText("");
            paintEnclosures();
        } catch (IllegalStateException f) {
            Utilities.showErrorMessage(f.getMessage(), this.component);
        } catch (NullPointerException | IllegalArgumentException n) {
            Utilities.showErrorMessage("No Animal Detected", this.component);
        }
    }

    private void changeList() {
        String data = list.getSelectedValue();
        if (data != null) {
            enclosureBio.setText(features.getAnimalBio(Species.valueOf(enclosureBioTitle.getText()), data, 'e'));
        }
    }
}
