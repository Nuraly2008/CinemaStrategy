
package pricing;

public interface PricingStrategy {
    double calculate(double price, int tickets, boolean weekday);
}

