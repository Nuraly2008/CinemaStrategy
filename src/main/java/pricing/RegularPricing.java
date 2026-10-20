
package pricing;

public class RegularPricing implements PricingStrategy {

    @Override
    public double calculate(double price, int tickets, boolean weekday) {
        return price * tickets;
    }
}
