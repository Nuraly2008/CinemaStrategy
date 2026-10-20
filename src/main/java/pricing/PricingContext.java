
package pricing;

import java.util.Objects;

public class PricingContext {

    private PricingStrategy strategy;

    public PricingContext(PricingStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public double calculate(double price, int tickets, boolean weekday) {
        return strategy.calculate(price, tickets, weekday);
    }
}

