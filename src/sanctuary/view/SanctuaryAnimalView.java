package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;

public class SanctuaryAnimalView extends JFrame implements SanctuaryView {


    private SanctuaryFeatures features;
    private IsolationPanel isolationPanel;
    private EnclosurePanel enclosurePanel;
    private AddPanel addPanel;
    private DashboardPanel dashboardPanel;
    private SearchPanel searchPanel;


    public SanctuaryAnimalView() {
        super();
        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void makeVisible() {
        this.setVisible(true);
    }

    public void packView(SanctuaryFeatures features){

        this.features = features;
        this.setTitle(features.sanctuaryName());

        isolationPanel = new IsolationPanel(features, this);
        enclosurePanel = new EnclosurePanel(features, this);
        addPanel = new AddPanel(features, this);
        dashboardPanel = new DashboardPanel(features,this);

        this.addMenu();
        this.addSearchArea();
        this.pack();
    }

    private void addSearchArea() {

        SearchPanel searchPanel = new SearchPanel(features,this);
        this.add(searchPanel.addSearchArea(), BorderLayout.SOUTH);
    }

    private void addMenu() {
        JTabbedPane tabs = new JTabbedPane();

        tabs.add("Dashboard",dashboardPanel.createDashboardFolder());
        tabs.add("Add Animal", addPanel.createAddFolder());
        tabs.add("Isolation", isolationPanel.createIsolationFolder());
        tabs.add("Enclosure", enclosurePanel.createEnclosureFolder());

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
    }


    @Override
    public void refresh() {this.repaint();}


    public void setDashboardInfo(String text){this.dashboardPanel.setDashboardInfo(text);}

    public void updateEnclosureArea() {this.enclosurePanel.updateEnclosureArea();}

    public void updateIsolationArea() {this.isolationPanel.updateIsolationArea();}








}
