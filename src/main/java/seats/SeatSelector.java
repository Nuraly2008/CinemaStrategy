
package seats;

public class SeatSelector {

    public SeatStrategy selectAutomatic(
            int people, boolean preferCenter) {

        if (people <= 0) {
            throw new IllegalArgumentException("Invalid people count");
        }

        if (people >= 2) {
            return new TogetherSeatStrategy();
        }

        if (preferCenter) {
            return new CenterSeatStrategy();
        }

        return new FrontSeatStrategy();
    }
}

