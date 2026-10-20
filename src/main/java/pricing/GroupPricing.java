
package pricing;

public class GroupPricing implements PricingStrategy {

    @Override
    public double calculate(double price, int tickets, boolean weekday) {
        int freeTickets = tickets / 4;
        return price * (tickets - freeTickets);
    }
}
