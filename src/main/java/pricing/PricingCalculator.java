
package pricing;

public class PricingCalculator {

    public double calculate(String type, double price,
                            int tickets, boolean weekday) {

        if (price < 0 || tickets <= 0) {
            throw new IllegalArgumentException("Invalid ticket data");
        }

        switch (type) {
            case "regular":
                return price * tickets;

            case "student":
                if (weekday) {
                    return price * tickets * 0.8;
                }
                return price * tickets;

            case "group":
                int freeTickets = tickets / 4;
                return price * (tickets - freeTickets);

            default:
                throw new IllegalArgumentException("Unknown pricing type");
        }
    }
}

