package common;

import configuration.EnvironmentSetup;
import pages.LandingPage;

/**
 * Created by aszymanski on 10/26/2016.
 */
public class CommonHelpers extends EnvironmentSetup {
    protected static LandingPage landingPage;

    protected static void initPages() {
        landingPage = new LandingPage(driver);
    }
}
