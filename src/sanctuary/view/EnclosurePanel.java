package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * This class represents an Enclosure Panel. It is used to create a Panel in the habitat
 */
public class EnclosurePanel extends JFrame {

    private  JTextArea enclosureBio;
    private JLabel enclosureBioTitle;
    private JPanel enclosureArea;
    private JList<String> list;
    private final DefaultListModel<String> membersList = new DefaultListModel<>();
    private final SanctuaryFeatures features;
    private final Component component;


    /**
     * Constructor that accepts a features class (actions) and a global component to append child components
     * @param features Controller the brain of this application
     * @param component Global component to append the enclosure panel
     */
    public EnclosurePanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    /**
     * Method that will create and rendering the Enclosure Panel folder
     * @return The components of the Enclosure Folder glue to a unique JPanel
     */
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
        for (String group: Utilities.getEnumNames(Species.class)) {
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

    /**
     * Create the habitat of each specie
     * @param group The name of the Species to create the room
     */
    private void createEnvironments(String group) {
        JButton subGroup = new JButton();
        subGroup.setName(group);
        subGroup.setSize(new Dimension(400,400));
        subGroup.setText(group);

        subGroup.addActionListener(x -> {
            enclosureBioTitle.setText(group);
            updateAnimalsInGroup(group);
            enclosureBio.setText("");
        });
        enclosureArea.add(subGroup);
    }

    /**
     * Every time there is a change in the enclosure, the habitat gets updated repainting the information
     */
    public void updateEnclosureArea() {

        paintEnclosures();
        enclosureBioTitle.setText("Members");
        enclosureBio.setText("");
        membersList.removeAllElements();
    }

    /**
     * Display the animals in the selected species group,
     * @param group The species group to display the animals in it.
     */
    private void updateAnimalsInGroup(String group) {
        membersList.removeAllElements();
        for (String en :features.displayAnimalsInEnclosureGroup(Species.valueOf(group)) ) {
            membersList.addElement(en);
        }
    }

    /**
     * The enclosure will be updated everytime there is a change, color red
     * represents animals living in the habitat. gray there is not animal
     * This will also display the number of animals per habitat
     */
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

    /**
     * Action to feed an animal.
     */
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

    /**
     * Action to move an animal to the Isolation
     */
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

    /**
     * action triggered when the user selects an animal from the list
     */
    private void changeList() {
        String data = list.getSelectedValue();
        if (data != null) {
            enclosureBio.setText(features.getAnimalBio(Species.valueOf(enclosureBioTitle.getText()), data, 'e'));
        }
    }
}
