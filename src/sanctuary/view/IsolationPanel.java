package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Species;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class IsolationPanel extends JFrame {

    private JPanel isolationArea;
    private JTextArea isolationBio;
    private JLabel isolationBioTitle;
    private final SanctuaryFeatures features;
    private final Component component;


    public IsolationPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createIsolationFolder() {

        JPanel container = new JPanel();
        JTextArea panel = Utilities.createContainer();

        //title
        panel.add(Utilities.createTitle("Isolation area"));

        //Display Monkeys Isolation
        isolationArea = Utilities.createGridRooms();
        panel.add(isolationArea);

        //Bio area
        isolationBio = Utilities.bioPanel();
        isolationBioTitle = new JLabel();
//        isolationBioTitle.setSize(100, 50);
        isolationBioTitle.setLocation(800, 250);
        panel.add(isolationBio);
        panel.add(isolationBioTitle);

        //Add Buttons
        JButton healthButton = Utilities.addButton("Medical Attention (PMA)", 200, 550);
        healthButton.addActionListener(e -> this.healthAction());
        panel.add(healthButton);

        JButton moveButton = Utilities.addButton("Move to Enclosure", 400,550);
        moveButton.addActionListener(e -> this.moveAction());
        panel.add(moveButton);

        container.add(panel);

        return container;
    }

    public void updateIsolationArea() {

        ArrayList<String> animalsIsolation = features.displayAllAnimalsNames('i');
        isolationArea.removeAll();
        isolationBio.setText("");
        isolationBioTitle.setText("");
        for (String monkey : animalsIsolation) {
            JButton jail = new JButton();
            jail.setText(monkey);
            jail.setSize(new Dimension(400,400));
            jail.setForeground(new Color(32, 137, 203));
            jail.addActionListener(e-> {
                isolationBio.setText(features.getAnimalBio(Species.Howler,monkey,'i'));
                isolationBioTitle.setText(monkey);
            });
            isolationArea.add(jail);
        }

        int emptyFields = 20 - animalsIsolation.size();

        for (int i = 0; i < emptyFields; i++) {
            JButton jail = new JButton();
            jail.setText("Empty");
            jail.setSize(500,500);
            jail.setForeground(new Color(92, 113, 131));
            jail.addActionListener(e-> {
                isolationBio.setText("Empty");
                isolationBioTitle.setText("");
            });

            isolationArea.add(jail);
        }
    }

    private void moveAction() {

        String name = isolationBioTitle.getText();
        try {
            features.transferAnimalToEnclosure(name);
            features.updateIsolation();
        } catch (IllegalStateException | IllegalArgumentException f) {
            Utilities.showErrorMessage(f.getMessage(), this.component);
        }
    }

    private void healthAction() {
        String name = isolationBioTitle.getText();
        try{
            features.provideMedicalAttention(name);
            isolationBio.setText(features.getAnimalBio(Species.Howler,name,'i'));
        } catch (IllegalStateException | IllegalArgumentException f){
            Utilities.showErrorMessage(f.getMessage(),this.component);
        }
    }
}
