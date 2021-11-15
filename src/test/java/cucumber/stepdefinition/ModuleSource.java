package cucumber.stepdefinition;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Stage;
import drivermanager.DriverModule;
import io.cucumber.guice.CucumberModules;
import io.cucumber.guice.InjectorSource;

public class ModuleSource implements InjectorSource {

    @Override
    public Injector getInjector() {
        return Guice.createInjector(Stage.DEVELOPMENT,
                CucumberModules.createScenarioModule(),
                new DriverModule());
    }
}
