
import data.DemandSource;
import org.junit.jupiter.api.Test;
import pricing.*;

import static org.junit.jupiter.api.Assertions.*;

public class PricingRulesTest {

    @Test
    void regularStrategyTest() {
        PricingStrategy strategy = new RegularPricing();
        assertEquals(8000, strategy.calculate(2000, 4, true));
    }

    @Test
    void studentStrategyTest() {
        DemandSource fake = () -> 30;
        PricingStrategy strategy = new StudentPricing(fake);

        assertEquals(6400, strategy.calculate(2000, 4, true));
    }

    @Test
    void groupStrategyTest() {
        PricingStrategy strategy = new GroupPricing();

        assertEquals(6000, strategy.calculate(2000, 4, true));
    }

    @Test
    void highDemandTest() {
        DemandSource fake = () -> 90;
        PricingStrategy strategy = new StudentPricing(fake);

        assertEquals(8000, strategy.calculate(2000, 4, true));
    }

    @Test
    void automaticGroupTest() {
        PricingSelector selector = new PricingSelector();

        assertInstanceOf(
                GroupPricing.class,
                selector.selectAutomatic(true, 4, true)
        );
    }

    @Test
    void automaticStudentTest() {
        PricingSelector selector = new PricingSelector();

        assertInstanceOf(
                StudentPricing.class,
                selector.selectAutomatic(true, 2, true)
        );
    }

    @Test
    void automaticRegularTest() {
        PricingSelector selector = new PricingSelector();

        assertInstanceOf(
                RegularPricing.class,
                selector.selectAutomatic(false, 2, true)
        );
    }

    @Test
    void runtimeSwitchTest() {
        PricingContext context = new PricingContext(
                new RegularPricing()
        );

        assertEquals(8000, context.calculate(2000, 4, true));

        context.setStrategy(new GroupPricing());

        assertEquals(6000, context.calculate(2000, 4, true));
    }
}

