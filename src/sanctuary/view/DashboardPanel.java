package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

/**
 * This class represents a Dashboard Panel. It is used to create a Panel in the habitat
 */
public class DashboardPanel {

    private JTextArea dashboardInfo;
    private final SanctuaryFeatures features;
    private final Component component;

    /**
     * Constructor that accepts a features class (actions) and a global component to append child components
     * @param features Controller the brain of this application
     * @param component Global component to append the dashboard panel
     */
    public DashboardPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

    /**
     * Method that will create and rendering the Dashboard Panel folder
     * @return The components of the dashboard Folder glue to a unique JPanel
     */
    public JPanel createDashboardFolder() {

        JPanel panel = new JPanel();

        JTextArea bk = new JTextArea(200,100);
        bk.setLayout(null);
        bk.setEnabled(false);
        bk.setOpaque(false);

        JLabel image = new JLabel("");
        URL logoPath = this.getClass().getClassLoader().getResource("logoJF.png");
        if(logoPath != null) {
            ImageIcon icon = new ImageIcon(logoPath);
            Image image2 = icon.getImage();
            Image image3 = image2.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
            icon = new ImageIcon(image3);
            image.setIcon(icon);
        }

        image.setSize(200, 200);
        image.setLocation(100, 80);
        bk.add(image);

        JLabel copyright = new JLabel("Image Copyright ©, Jungle Friends Primate Sanctuary.");
        copyright.setLocation(25,680);
        copyright.setSize(400,80);

        bk.add(copyright);

        JPanel menuLateral = new JPanel();
        menuLateral.setSize(400,800);
        menuLateral.setLocation(0,0);
        menuLateral.setBackground(new Color(235, 224, 181));

        bk.add(menuLateral);

        dashboardInfo = new JTextArea();
        dashboardInfo.setEnabled(false);
        dashboardInfo.setOpaque(false);
        dashboardInfo.setSize(900, 500);
        dashboardInfo.setLocation(450, 150);
        dashboardInfo.setBorder(new EmptyBorder(10, 10, 10, 10));

        bk.add(dashboardInfo);

        //Title
        bk.add(panel.add(Utilities.createTitle("Jungle Friends")));

        panel.add(bk);

        return panel;

    }

    /**
     * Update the information of the habitat
     * @param text Information with habitat's Bio
     */
    public void setDashboardInfo(String text){
        this.dashboardInfo.setText(text);
    }

}
