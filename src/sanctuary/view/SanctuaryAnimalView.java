package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;
import sanctuary.utils.Food;
import sanctuary.utils.Sex;
import sanctuary.utils.Species;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class SanctuaryAnimalView extends JFrame implements SanctuaryView {

    JFrame frame;
    private JButton commandButton, quitButton;
    private JPanel buttonPanel;
    private JPanel turtlePanel;
    private JScrollPane scrollPane;
    private JTextField input;
    private JTextArea dashboardInfo;
    private JPanel isolationArea;
    private JTextArea isolationBio;
    private JLabel isolationBioTitle;
    private JTextArea enclosureBio;
    private JLabel enclosureBioTitle;
    private SanctuaryFeatures features;

    private DefaultListModel<String> l1 = new DefaultListModel<>();

    public SanctuaryAnimalView() {
        super();
        this.setTitle("Sanctuary");
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        URL femiPath = this.getClass().getClassLoader().getResource("logoJF.png");
        if(femiPath != null) {
            Image icon = new ImageIcon(femiPath).getImage();
            this.setIconImage(icon);
        }

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
            String searched = input.getText().trim();
            if (searched.length() > 0) {

                try{
                    String[] location = features.searchAnimalByName(searched);
                    showInfoMessage("Monkey Location" , "Location: " + location[0] + "\n\n" + location[1]);
                    input.setText("");
                } catch (IllegalArgumentException d ) {
                    showErrorMessage(d.getMessage());
                }
            }
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

        tabs.addChangeListener((ChangeEvent e) -> {

                switch (tabs.getSelectedIndex()) {
                    case 0:
                        features.updateDashboard();
                        break;
                    case 1:

                        break;
                    case 2:
                        features.updateIsolation();
                        break;
                    case 3:
                        features.updateEnclosure();
                        break;
                }
        });

        this.add(tabs);


        this.pack();


    }


    public void makeVisible() {
        this.setVisible(true);
    }

    public void addFeatures(SanctuaryFeatures features){
        this.features = features;
    }



    @Override
    public void refresh() {
        this.repaint();
    }


    public void showErrorMessage(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);

    }

    public void showInfoMessage(String title, String info) {
        JOptionPane.showMessageDialog(this, info, title, JOptionPane.INFORMATION_MESSAGE);

    }

    private JPanel createDashboardFolder() {

        JPanel panel = new JPanel();

        JTextArea bk = new JTextArea(200,100);
        bk.setLayout(null);
        bk.setEnabled(false);
        bk.setOpaque(false);

        dashboardInfo = new JTextArea();

        dashboardInfo.setEnabled(false);
        dashboardInfo.setOpaque(false);
        dashboardInfo.setSize(900, 500);

        dashboardInfo.setLocation(450, 150);
        dashboardInfo.setBorder(new EmptyBorder(10, 10, 10, 10));

        bk.add(dashboardInfo);

        JLabel title = new JLabel("Jungle Friends");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);
        bk.add(title);


        JLabel image = new JLabel("");
//        image.setFont(new Font("Arial", Font.BOLD, 30));
        URL logoPath = this.getClass().getClassLoader().getResource("logoJF.png");
        if(logoPath != null) {
            ImageIcon icon = new ImageIcon(logoPath);
            Image image2 = icon.getImage();
            Image image3 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(image3);
            image.setIcon(icon);
        }

        image.setSize(200, 200);
        image.setLocation(200, 150);
        bk.add(image);

        panel.add(bk);

        return panel;

    }

    public void setDashboardInfo(String text){
        this.dashboardInfo.setText(text);
    }

    public void updateEnclosureArea() {

        enclosureBioTitle.setText("Members");
        enclosureBio.setText("");
        l1.removeAllElements();
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
    private JPanel createIsolationFolder() {
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
            } catch (IllegalStateException f) {
                showErrorMessage(f.getMessage());
            }
        });
        panel.add(moveButton);

        JButton actionButton = new JButton("Provide Medical Attention (PMA)");
        actionButton.addActionListener(e -> {
            String name = isolationBioTitle.getText();

            try{
                features.provideMedicalAttention(name);
                isolationBio.setText(features.getAnimalBio(Species.Howler,name,'i'));
            } catch (IllegalStateException f){
                showErrorMessage(f.getMessage());
            }

        });
        actionButton.setSize(200, 30);
        actionButton.setLocation(400, 550);
        panel.add(actionButton);

        return panel;
    }

    private JPanel createEnclosureFolder() {
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
        enclosureBio.setSize(300, 200);
        enclosureBio.setLocation(800, 200);
        enclosureBio.setBorder(new EmptyBorder(10, 10, 10, 10));

        enclosureBioTitle = new JLabel("Members");
        enclosureBioTitle.setSize(200, 30);
        enclosureBioTitle.setLocation(800, 420);
        enclosureBioTitle.setFont(new Font("Arial", Font.BOLD, 15));


        for (String group: this.getNames(Species.class)) {
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
                showErrorMessage(f.getMessage());
            }

        });
        moveButton.setSize(200, 30);
        moveButton.setLocation(200, 550);
        panel.add(moveButton);

        JButton actionButton = new JButton("Feed");
        actionButton.addActionListener(x-> {

            showInfoMessage("Food Food","Ook-ook! \n" +
                    features.getFavoriteFood(Species.valueOf(enclosureBioTitle.getText()), list.getSelectedValue())
                    + "\nEeek-aak-eek!"
            );
        });
        actionButton.setSize(200, 30);
        actionButton.setLocation(400, 550);
        panel.add(actionButton);


        return panel;
    }

    private JPanel createAddFolder(){
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

        String speciesList[] = this.getNames(Species.class);
        speciesC= new JComboBox(speciesList);
        speciesL = new JLabel("Species:");
        speciesL.setSize(300, 30);
        speciesC.setSize(100, 30);
        speciesC.setLocation(250, 250);
        speciesL.setLocation(200, 250);
        p2.add(speciesL);
        p2.add(speciesC);

        //sex
        String sexList[] = this.getNames(Sex.class);
        sexC= new JComboBox(sexList);
        sexL = new JLabel("Sex:");
        sexL.setSize(300, 30);
        sexC.setSize(100, 30);
        sexC.setLocation(250, 300);
        sexL.setLocation(200, 300);
        p2.add(sexL);
        p2.add(sexC);

        //Food
        String foodList[] = this.getNames(Food.class);
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

//        tp.setBounds(150,250,100,200);

        return p2;

    }

    private void updateAnimalsInGroup(String group) {
        l1.removeAllElements();
        for (String en :features.displayAnimalsInEnclosureGroup(Species.valueOf(group)) ) {
            l1.addElement(en);
        }
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
