
package seats;

import java.util.Objects;

public class SeatContext {

    private SeatStrategy strategy;

    public SeatContext(SeatStrategy strategy) {
        setStrategy(strategy);
    }

    public void setStrategy(SeatStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }

    public int[] selectSeats(boolean[] available, int count) {
        return strategy.selectSeats(available, count);
    }
}

