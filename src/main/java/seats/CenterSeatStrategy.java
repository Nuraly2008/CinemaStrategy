
package seats;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CenterSeatStrategy implements SeatStrategy {

    @Override
    public int[] selectSeats(boolean[] available, int count) {
        if (count <= 0) {
            throw new IllegalArgumentException("Invalid seat count");
        }

        List<Integer> seats = new ArrayList<>();

        for (int i = 0; i < available.length; i++) {
            if (available[i]) {
                seats.add(i);
            }
        }

        if (seats.size() < count) {
            throw new IllegalStateException("Not enough seats");
        }

        double center = (available.length - 1) / 2.0;

        seats.sort(
                Comparator.comparingDouble(
                        (Integer i) -> Math.abs(i - center)
                ).thenComparingInt(i -> i)
        );

        return seats.stream()
                .limit(count)
                .mapToInt(Integer::intValue)
                .toArray();
    }
}

