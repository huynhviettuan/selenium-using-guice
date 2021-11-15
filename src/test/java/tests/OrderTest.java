package tests;

import com.google.inject.Inject;
import drivermanager.DriverModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import pages.PaymentGatewayPage;

@Guice(modules = {
        DriverModule.class
})
public class OrderTest extends BaseTest {

    @Inject
    PaymentGatewayPage paymentGatewayPage;

    @Test
    public void when_user_order_successfully() {
        paymentGatewayPage.orderBabiesSoftToy("2");
    }
}
