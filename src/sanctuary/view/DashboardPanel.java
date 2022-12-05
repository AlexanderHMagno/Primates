package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.net.URL;

public class DashboardPanel {

    private JTextArea dashboardInfo;
    private SanctuaryFeatures features;
    private Component component;

    public DashboardPanel(SanctuaryFeatures features, Component component) {
        this.features = features;
        this.component = component;
    }

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

        JLabel title = new JLabel("Jungle Friends");
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setSize(400, 100);
        title.setLocation(500, 50);
        bk.add(title);

        panel.add(bk);

        return panel;

    }

    public void setDashboardInfo(String text){
        this.dashboardInfo.setText(text);
    }

}
