
package pricing;

public class PricingCalculator {

    private final PricingSelector selector = new PricingSelector();

    public double calculate(String type, double price,
                            int tickets, boolean weekday) {

        if (price < 0 || !Double.isFinite(price) || tickets <= 0) {
            throw new IllegalArgumentException("Invalid ticket data");
        }

        PricingStrategy strategy = selector.selectManual(type);
        PricingContext context = new PricingContext(strategy);

        return context.calculate(price, tickets, weekday);
    }
}

