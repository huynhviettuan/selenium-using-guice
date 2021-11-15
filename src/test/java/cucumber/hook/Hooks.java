package cucumber.hook;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;
import common.BrowserInteractions;
import common.Constants;
import org.junit.Before;

@Singleton
public class Hooks {

    @Inject
    private Injector injector;

    @Inject
    BrowserInteractions browserInteractions;

    @Before()
    public void setupScenario() {
        browserInteractions.goTo(Constants.DEFAULT_URL);
    }

}
