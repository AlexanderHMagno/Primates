package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class AddPanel extends JFrame {



    private SanctuaryFeatures features;
    private Component component;


    public AddPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    public JPanel createAddFolder(){
        JPanel p2= new JPanel();
        p2.setLayout(null);
        JLabel nameL, sexL, speciesL, sizeL, weightL, ageL, foodL, title;
        JComboBox sexC, speciesC,foodC;

        title = new JLabel("Add new Monkey");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);

        p2.add(title);

        //name
        JTextField name = new JTextField("",15);
        nameL = new JLabel("Name:");
        nameL.setSize(300, 30);
        name.setSize(300, 30);
        name.setLocation(250, 200);
        nameL.setLocation(200, 200);
        p2.add(nameL);
        p2.add(name);

        //Species

        String speciesList[] = Utilities.getNames(Species.class);
        speciesC= new JComboBox(speciesList);
        speciesL = new JLabel("Species:");
        speciesL.setSize(300, 30);
        speciesC.setSize(100, 30);
        speciesC.setLocation(250, 250);
        speciesL.setLocation(200, 250);
        p2.add(speciesL);
        p2.add(speciesC);

        //sex
        String sexList[] = Utilities.getNames(Sex.class);
        sexC= new JComboBox(sexList);
        sexL = new JLabel("Sex:");
        sexL.setSize(300, 30);
        sexC.setSize(100, 30);
        sexC.setLocation(250, 300);
        sexL.setLocation(200, 300);
        p2.add(sexL);
        p2.add(sexC);

        //Food
        String foodList[] = Utilities.getNames(Food.class);
        foodC= new JComboBox(foodList);
        foodL = new JLabel("Favorite Food:");
        foodL.setSize(300, 30);
        foodC.setSize(100, 30);
        foodC.setLocation(300, 350);
        foodL.setLocation(200, 350);
        p2.add(foodL);
        p2.add(foodC);

        //size
        SpinnerModel sizeFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        JSpinner sizeC = new JSpinner(sizeFormat);
        sizeL = new JLabel("Size: ");
        sizeL.setSize(300, 30);
        sizeC.setSize(100, 30);
        sizeC.setLocation(250, 400);
        sizeL.setLocation(200, 400);
        p2.add(sizeL);
        p2.add(sizeC);

        //Weight
        SpinnerModel weightFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        JSpinner weightC = new JSpinner(weightFormat);
        weightL = new JLabel("Weight: ");
        weightL.setSize(300, 30);
        weightC.setSize(100, 30);
        weightC.setLocation(250, 450);
        weightL.setLocation(200, 450);
        p2.add(weightL);
        p2.add(weightC);

        //Age
        SpinnerModel ageFormat = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner ageC = new JSpinner(ageFormat);
        ageL = new JLabel("Age: ");
        ageL.setSize(300, 30);
        ageC.setSize(100, 30);
        ageC.setLocation(250, 500);
        ageL.setLocation(200, 500);
        p2.add(ageL);
        p2.add(ageC);


        JTextArea area1 = new JTextArea();
        area1.setSize(300, 200);
        area1.setLocation(800, 200);
        area1.setEnabled(false);
        area1.setBorder(new EmptyBorder(10, 10, 10, 10));
        area1.setOpaque(true);
        p2.add(area1);

        JTextArea areaResponse =new JTextArea();
        areaResponse.setSize(300, 50);
        areaResponse.setEnabled(false);
        areaResponse.setOpaque(false);
        areaResponse.setLineWrap(true);
        areaResponse.setWrapStyleWord(true);
        areaResponse.setBorder(new EmptyBorder(10, 10, 10, 10));
        areaResponse.setLocation(800, 400);

        p2.add(areaResponse);

        JButton createButton = new JButton("Add Animal");
        createButton.setSize(200, 30);
        createButton.setLocation(200, 550);
        createButton.addActionListener(e -> {

            try{
                String nameF = name.getText().trim().length() > 0 ? name.getText() : " ";
                features.addAnimal(
                        nameF,
                        Species.valueOf(speciesC.getSelectedItem().toString()),
                        Sex.valueOf(sexC.getSelectedItem().toString()),
                        (double) sizeC.getValue(),
                        (double) weightC.getValue(),
                        (int) ageC.getValue(),
                        Food.valueOf(foodC.getSelectedItem().toString())
                );


                area1.setText(features.getAnimalBio(Species.valueOf(speciesC.getSelectedItem().toString()),name.getText(),'i'));

                name.setText("");
                areaResponse.setText("Monkey has been accepted");
            } catch (IllegalArgumentException userError) {
                areaResponse.setText(userError.getMessage());
            } catch (IllegalStateException adminError) {
                areaResponse.setText(adminError.getMessage());
            }


        });
        p2.add(createButton);

        JButton resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> {
            name.setText("");
            speciesC.setSelectedIndex(0);
            sexC.setSelectedIndex(0);
            sizeC.setValue(0.00);
            weightC.setValue(0.00);
            ageC.setValue(0);
            foodC.setSelectedIndex(0);

            areaResponse.setText("");
            area1.setText("");
        });
        resetButton.setSize(200, 30);
        resetButton.setLocation(400, 550);
        p2.add(resetButton);


        return p2;

    }


}
