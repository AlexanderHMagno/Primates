package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

/**
 * This interface represents the view of the sanctuary
 */
public interface SanctuaryView {

    /**
     * Display component in the view
     */
    void makeVisible();

    /**
     * Update the information on Dashboard
     * @param text Information to be displayed
     */
    void setDashboardInfo(String text);

    /**
     * Group all components and render the view
     * @param features controller with actions
     */
    void packView(SanctuaryFeatures features);

    /**
     * Repaint the isolation if there is a change in it
     */
    void updateIsolationArea();

    /**
     * Repaint the enclosure if there is a change in it
     */
    void updateEnclosureArea();
}
