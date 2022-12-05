package sanctuary.view;

import sanctuary.controller.SanctuaryFeatures;

public interface SanctuaryView {


    void refresh();

    void makeVisible();

    void setDashboardInfo(String text);

    void packView(SanctuaryFeatures features);

    void updateIsolationArea();

    void updateEnclosureArea();
}
