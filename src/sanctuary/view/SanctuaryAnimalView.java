package sanctuary.view;

import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Arrays;

public class SanctuaryAnimalView extends JFrame implements SanctuaryView {

    JFrame frame;
    private JButton commandButton, quitButton;
    private JPanel buttonPanel;
    private JPanel turtlePanel;
    private JScrollPane scrollPane;
    private JTextField input;
    private JTextArea dashboardInfo;

    public SanctuaryAnimalView() {
        super();
        this.setTitle("Sanctuary");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //use a borderlayout with drawing panel in center and button panel in south
        this.setLayout(new BorderLayout());
        turtlePanel = new JPanel();
//        turtlePanel.setPreferredSize(new Dimension(500, 500));
        scrollPane = new JScrollPane(turtlePanel);
        this.add(scrollPane, BorderLayout.CENTER);

        //button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        this.add(buttonPanel, BorderLayout.SOUTH);

        //input textfield
        input = new JTextField(15);
        buttonPanel.add(input);

        //buttons
        commandButton = new JButton("Search");
        commandButton.addActionListener((ActionEvent e) ->
        {
//            if (commandCallback != null) { //if there is a command callback
//                commandCallback.accept(input.getText()); //send command to be processed
//                input.setText(""); //clear the input text field
//            }
        });
        buttonPanel.add(commandButton);

        //quit button
        quitButton = new JButton("Quit");
        quitButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        buttonPanel.add(quitButton);




        /// Add tabs


        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Dashboard",createDashboardFolder());
        tabs.add("Add Animal",createAddFolder());
        tabs.add("Isolation",createIsolationFolder());
        tabs.add("Enclosure",createEnclosureFolder());


        this.add(tabs);


        this.pack();


    }


    public void makeVisible() {
        this.setVisible(true);
    }



    @Override
    public void refresh() {
        this.repaint();
    }


    public void showErrorMessage(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

    }

    private JPanel createDashboardFolder() {

        JPanel panel = new JPanel();
//        panel.setLayout(null);

        JLabel title = new JLabel("Dashboard");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(600, 50);
        panel.add(title);

        dashboardInfo = new JTextArea(200,100);
        dashboardInfo.setEnabled(false);
        dashboardInfo.setText("Practicing");
        dashboardInfo.setSize(900, 500);

        dashboardInfo.setLocation(200, 200);
        panel.add(dashboardInfo);

        return panel;

    }

    public void setDashboardInfo(String text){
        this.dashboardInfo.setText(text);
    }
    private JPanel createIsolationFolder() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Isolation area");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(600, 50);
        panel.add(title);

        //Display Monkeys Isolation

        JPanel isolationArea = new JPanel();
        isolationArea.setSize(400, 300);
        isolationArea.setLocation(200,  200);

        for (int i = 0; i < 20 ; i++) {
            JButton jail = new JButton();
            jail.setText("Monkey " + (i + 1));
            isolationArea.add(jail);
        }

        panel.add(isolationArea);

        JTextArea area1 = new JTextArea();
        area1.setSize(300, 350);
        area1.setLocation(900, 200);
        panel.add(area1);

        JButton moveButton = new JButton("Move to Enclosure");
        moveButton.setSize(200, 30);
        moveButton.setLocation(250, 550);
        panel.add(moveButton);

        JButton actionButton = new JButton("Provide Medical Attention (PMA)");
        actionButton.setSize(200, 30);
        actionButton.setLocation(450, 550);
        panel.add(actionButton);

        return panel;
    }

    private JPanel createEnclosureFolder() {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        JLabel title = new JLabel("Enclosure area");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(600, 50);
        panel.add(title);

        //Habitat
        JPanel enclosureArea = new JPanel();
        enclosureArea.setSize(400, 300);
        enclosureArea.setLocation(200,  200);

        for (String group: this.getNames(Species.class)) {
            JButton subGroup = new JButton();
            subGroup.setText(group);
            enclosureArea.add(subGroup);
        }

        panel.add(enclosureArea);

        JButton moveButton = new JButton("Move to Isolation");
        moveButton.setSize(200, 30);
        moveButton.setLocation(250, 550);
        panel.add(moveButton);

        JButton actionButton = new JButton("Feed");
        actionButton.setSize(200, 30);
        actionButton.setLocation(450, 550);
        panel.add(actionButton);




        return panel;
    }

    private JPanel createAddFolder(){
        JPanel p2= new JPanel();
        p2.setLayout(null);
        JLabel nameL, sexL, speciesL, sizeL, weightL, ageL, foodL, title;
        JComboBox sexC, speciesC,foodC;

        //name
        JTextField name = new JTextField("",15);
        nameL = new JLabel("Name:");
        nameL.setSize(300, 30);
        name.setSize(300, 30);
        name.setLocation(350, 200);
        nameL.setLocation(250, 200);
        p2.add(nameL);
        p2.add(name);

        //Species

        String speciesList[] = this.getNames(Species.class);
        speciesC= new JComboBox(speciesList);
        speciesL = new JLabel("Species:");
        speciesL.setSize(300, 30);
        speciesC.setSize(100, 30);
        speciesC.setLocation(300, 250);
        speciesL.setLocation(250, 250);
        p2.add(speciesL);
        p2.add(speciesC);

        //sex
        String sexList[] = this.getNames(Sex.class);
        sexC= new JComboBox(sexList);
        sexL = new JLabel("Sex:");
        sexL.setSize(300, 30);
        sexC.setSize(100, 30);
        sexC.setLocation(300, 300);
        sexL.setLocation(250, 300);
        p2.add(sexL);
        p2.add(sexC);

        //Food
        String foodList[] = this.getNames(Food.class);
        foodC= new JComboBox(foodList);
        foodL = new JLabel("Favorite Food:");
        foodL.setSize(300, 30);
        foodC.setSize(100, 30);
        foodC.setLocation(350, 350);
        foodL.setLocation(250, 350);
        p2.add(foodL);
        p2.add(foodC);

        //size
        SpinnerModel sizeFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        JSpinner sizeC = new JSpinner(sizeFormat);
        sizeL = new JLabel("Size: ");
        sizeL.setSize(300, 30);
        sizeC.setSize(100, 30);
        sizeC.setLocation(300, 400);
        sizeL.setLocation(250, 400);
        p2.add(sizeL);
        p2.add(sizeC);

        //Weight
        SpinnerModel weightFormat = new SpinnerNumberModel(0, 0, 200, 0.1);
        JSpinner weightC = new JSpinner(weightFormat);
        weightL = new JLabel("Weight: ");
        weightL.setSize(300, 30);
        weightC.setSize(100, 30);
        weightC.setLocation(300, 450);
        weightL.setLocation(250, 450);
        p2.add(weightL);
        p2.add(weightC);

        //Age
        SpinnerModel ageFormat = new SpinnerNumberModel(0, 0, 100, 1);
        JSpinner ageC = new JSpinner(ageFormat);
        ageL = new JLabel("Age: ");
        ageL.setSize(300, 30);
        ageC.setSize(100, 30);
        ageC.setLocation(300, 500);
        ageL.setLocation(250, 500);
        p2.add(ageL);
        p2.add(ageC);

        JButton createButton = new JButton("Add Animal");
        createButton.setSize(200, 30);
        createButton.setLocation(250, 550);
        p2.add(createButton);

        JButton resetButton = new JButton("Reset");
        resetButton.setSize(200, 30);
        resetButton.setLocation(450, 550);
        p2.add(resetButton);


        JTextArea area1 = new JTextArea();
        area1.setSize(300, 250);
        area1.setLocation(900, 200);
        p2.add(area1);

        JTextArea areaResponse =new JTextArea();
        areaResponse.setSize(300, 100);
        areaResponse.setLocation(900, 500);

        p2.add(areaResponse);

        title = new JLabel("Add new Monkey");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(600, 50);

        p2.add(title);

//        tp.setBounds(150,250,100,200);

        return p2;

    }

    /**
     * Get the names on Enums as an array of strings
     * @param e enum.class
     * @return return enum as an array of strings
     * Taken from: https://stackoverflow.com/questions/13783295/getting-all-names-in-an-enum-as-a-string
     */
    private String[] getNames(Class<? extends Enum<?>> e) {
        return Arrays.stream(e.getEnumConstants()).map(Enum::name).toArray(String[]::new);
    }



}
