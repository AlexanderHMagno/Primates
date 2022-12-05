package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class IsolationPanel extends JFrame {

    private JPanel isolationArea;
    private JTextArea isolationBio;
    private JLabel isolationBioTitle;
    private SanctuaryFeatures features;
    private Component component;


    public IsolationPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createIsolationFolder() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Isolation area");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);
        panel.add(title);

        //Display Monkeys Isolation

        isolationArea = new JPanel(new GridLayout(4,4,4,4));
        isolationArea.setSize(400, 300);
        isolationArea.setLocation(200, 200);

        panel.add(isolationArea);

        isolationBio = new JTextArea();
        isolationBio.setSize(300, 200);
        isolationBio.setLocation(800, 200);
        isolationBio.setBorder(new EmptyBorder(10, 10, 10, 10));

        isolationBioTitle = new JLabel();
        isolationBioTitle.setSize(100, 50);
        isolationBioTitle.setLocation(800, 250);

        panel.add(isolationBio);
        panel.add(isolationBioTitle);

        JButton moveButton = new JButton("Move to Enclosure");
        moveButton.setSize(200, 30);
        moveButton.setLocation(200, 550);
        moveButton.addActionListener(e-> {
            String name = isolationBioTitle.getText();

            try {

                features.transferAnimalToEnclosure(name);
                features.updateIsolation();
            } catch (IllegalStateException | IllegalArgumentException f) {
                Utilities.showErrorMessage(f.getMessage(), this.component);
            }
        });
        panel.add(moveButton);

        JButton actionButton = new JButton("Provide Medical Attention (PMA)");
        actionButton.addActionListener(e -> {
            String name = isolationBioTitle.getText();

            try{
                features.provideMedicalAttention(name);
                isolationBio.setText(features.getAnimalBio(Species.Howler,name,'i'));
            } catch (IllegalStateException | IllegalArgumentException f){
                Utilities.showErrorMessage(f.getMessage(),this.component);
            }

        });
        actionButton.setSize(200, 30);
        actionButton.setLocation(400, 550);
        panel.add(actionButton);

        return panel;
    }

    public void updateIsolationArea() {

        ArrayList<String> animalsIsolation = features.displayAllAnimalsNames('i');
        isolationArea.removeAll();
        isolationBio.setText("");
        isolationBioTitle.setText("");
        for (String monkey : animalsIsolation) {
            JButton jail = new JButton();
            jail.setText(monkey);
            jail.setSize(500,500);
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

}
