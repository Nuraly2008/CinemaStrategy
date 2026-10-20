
import org.junit.jupiter.api.Test;
import pricing.PricingCalculator;

import static org.junit.jupiter.api.Assertions.*;

public class PricingTest {

    private final PricingCalculator calculator = new PricingCalculator();

    @Test
    void regularPricingTest() {
        double result = calculator.calculate("regular", 2000, 4, true);
        assertEquals(8000, result);
    }

    @Test
    void studentWeekdayTest() {
        double result = calculator.calculate("student", 2000, 4, true);
        assertEquals(6400, result);
    }

    @Test
    void studentWeekendTest() {
        double result = calculator.calculate("student", 2000, 4, false);
        assertEquals(8000, result);
    }

    @Test
    void groupPricingTest() {
        double result = calculator.calculate("group", 2000, 4, true);
        assertEquals(6000, result);
    }

    @Test
    void groupFiveTicketsTest() {
        double result = calculator.calculate("group", 2000, 5, true);
        assertEquals(8000, result);
    }

    @Test
    void invalidTicketsTest() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("regular", 2000, 0, true));
    }

    @Test
    void unknownPricingTest() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.calculate("vip", 2000, 2, true));
    }
}

