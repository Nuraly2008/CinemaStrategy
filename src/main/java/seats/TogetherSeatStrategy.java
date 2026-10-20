
package seats;

public class TogetherSeatStrategy implements SeatStrategy {

    @Override
    public int[] selectSeats(boolean[] available, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Invalid seat count");
        }

        int consecutive = 0;

        for (int i = 0; i < available.length; i++) {
            if (available[i]) {
                consecutive++;
            } else {
                consecutive = 0;
            }

            if (consecutive == count) {
                int[] result = new int[count];

                for (int j = 0; j < count; j++) {
                    result[j] = i - count + 1 + j;
                }

                return result;
            }
        }

        throw new IllegalStateException("No adjacent seats available");
    }
}
