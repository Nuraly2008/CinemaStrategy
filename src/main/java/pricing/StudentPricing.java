
package pricing;

import data.DemandSource;
import data.SimulatedDemandSource;

import java.util.Objects;

public class StudentPricing implements PricingStrategy {

    private final DemandSource demandSource;

    public StudentPricing() {
        this(new SimulatedDemandSource());
    }

    public StudentPricing(DemandSource demandSource) {
        this.demandSource = Objects.requireNonNull(demandSource);
    }

    @Override
    public double calculate(double price, int tickets, boolean weekday) {
        double total = price * tickets;

        if (weekday && demandSource.getOccupancyPercent() < 80) {
            return total * 0.8;
        }

        return total;
    }
}
