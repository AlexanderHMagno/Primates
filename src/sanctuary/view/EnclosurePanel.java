package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class EnclosurePanel extends JFrame {

    private  JTextArea enclosureBio;
    private JLabel enclosureBioTitle;
    private DefaultListModel<String> l1 = new DefaultListModel<>();
    private SanctuaryFeatures features;
    private Component component;


    public EnclosurePanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createEnclosureFolder() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Enclosure area");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);
        panel.add(title);

        //Habitat
        JPanel enclosureArea = new JPanel(new GridLayout(4,4,4,4));
        enclosureArea.setSize(400, 300);
        enclosureArea.setLocation(200,  200);

        panel.add(enclosureArea);

        enclosureBio = new JTextArea();
        enclosureBio.setEnabled(false);
        enclosureBio.setSize(300, 200);
        enclosureBio.setLocation(800, 200);
        enclosureBio.setBorder(new EmptyBorder(10, 10, 10, 10));

        enclosureBioTitle = new JLabel("Members");
        enclosureBioTitle.setSize(200, 30);
        enclosureBioTitle.setLocation(800, 420);
        enclosureBioTitle.setFont(new Font("Arial", Font.BOLD, 15));


        for (String group: Utilities.getNames(Species.class)) {
            JButton subGroup = new JButton();
            subGroup.setSize(400,500);
            subGroup.setSize(new Dimension(400,400));
            subGroup.setBackground(new Color(32, 137, 203));
            subGroup.setMaximumSize(new Dimension(400, 400));
            subGroup.setForeground(new Color(206, 50, 50));
            subGroup.setText(group);

            subGroup.addActionListener(x -> {
                enclosureBioTitle.setText(group);
                updateAnimalsInGroup(group);
                enclosureBio.setText("");

            });
            enclosureArea.add(subGroup);
        }

        JList<String> list = new JList<>(l1);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        list.addListSelectionListener(x -> {
            String data = list.getSelectedValue();
            if (data != null) {
                enclosureBio.setText(features.getAnimalBio(Species.valueOf(enclosureBioTitle.getText()), data, 'e'));
            }

        });

        list.setSize(300,100);

        list.setBorder(new EmptyBorder(10, 10, 10, 10));
        list.setLocation(800,450);


        panel.add(enclosureBio);
        panel.add(enclosureBioTitle);
        panel.add(list);


        JButton moveButton = new JButton("Move to Isolation");
        moveButton.addActionListener(x-> {
            try {
                    features.transferAnimalToIsolation(Species.valueOf(enclosureBioTitle.getText()), list.getSelectedValue() );
                    updateAnimalsInGroup(enclosureBioTitle.getText());
                    enclosureBio.setText("");
            } catch (IllegalStateException f) {
                Utilities.showErrorMessage(f.getMessage(), this.component);
            } catch (NullPointerException | IllegalArgumentException n) {
                Utilities.showErrorMessage("No Animal Detected", this.component);
            }

        });
        moveButton.setSize(200, 30);
        moveButton.setLocation(200, 550);
        panel.add(moveButton);

        JButton actionButton = new JButton("Feed");
        actionButton.addActionListener(x-> {

            try {
                Utilities.showInfoMessage("Food Food", "Ook-ook! \n" +
                        features.getFavoriteFood(Species.valueOf(enclosureBioTitle.getText()), list.getSelectedValue())
                        + "\nEeek-aak-eek!", this.component
                );
            } catch (NullPointerException| IllegalArgumentException n) {
                Utilities.showErrorMessage("No Animal Detected", this.component);
            }
        });
        actionButton.setSize(200, 30);
        actionButton.setLocation(400, 550);
        panel.add(actionButton);


        return panel;
    }

    public void updateEnclosureArea() {

        enclosureBioTitle.setText("Members");
        enclosureBio.setText("");
        l1.removeAllElements();
    }

    private void updateAnimalsInGroup(String group) {
        l1.removeAllElements();
        for (String en :features.displayAnimalsInEnclosureGroup(Species.valueOf(group)) ) {
            l1.addElement(en);
        }
    }
}
