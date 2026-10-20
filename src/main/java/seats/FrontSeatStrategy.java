
package seats;

public class FrontSeatStrategy implements SeatStrategy {

    @Override
    public int[] selectSeats(boolean[] available, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Invalid seat count");
        }

        int[] result = new int[count];
        int found = 0;

        for (int i = 0; i < available.length; i++) {
            if (available[i]) {
                result[found++] = i;

                if (found == count) {
                    return result;
                }
            }
        }

        throw new IllegalStateException("Not enough seats");
    }
}
