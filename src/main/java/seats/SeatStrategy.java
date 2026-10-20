
package seats;

public interface SeatStrategy {
    int[] selectSeats(boolean[] available, int count);
}
