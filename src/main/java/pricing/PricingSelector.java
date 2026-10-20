
package pricing;

public class PricingSelector {

    public PricingStrategy selectManual(String type) {
        if (type == null) {
            throw new IllegalArgumentException("Unknown pricing type");
        }

        switch (type) {
            case "regular":
                return new RegularPricing();

            case "student":
                return new StudentPricing();

            case "group":
                return new GroupPricing();

            default:
                throw new IllegalArgumentException("Unknown pricing type");
        }
    }

    public PricingStrategy selectAutomatic(
            boolean student, int tickets, boolean weekday) {

        if (tickets >= 4) {
            return new GroupPricing();
        }

        if (student && weekday) {
            return new StudentPricing();
        }

        return new RegularPricing();
    }
}
