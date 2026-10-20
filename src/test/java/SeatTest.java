
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import seats.*;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

public class SeatTest {

    private final boolean[] available = {
            true, true, false, true, true,
            true, false, true, true, true
    };

    @Test
    void frontSeatTest() {
        SeatContext context = new SeatContext(
                new FrontSeatStrategy());

        assertArrayEquals(
                new int[]{0, 1},
                context.selectSeats(available, 2)
        );
    }

    @Test
    void centerSeatTest() {
        SeatContext context = new SeatContext(
                new CenterSeatStrategy());

        assertArrayEquals(
                new int[]{4, 5},
                context.selectSeats(available, 2)
        );
    }

    @Test
    void togetherSeatTest() {
        SeatContext context = new SeatContext(
                new TogetherSeatStrategy());

        assertArrayEquals(
                new int[]{0, 1},
                context.selectSeats(available, 2)
        );
    }

    @Test
    void automaticGroupTest() {
        SeatSelector selector = new SeatSelector();

        assertInstanceOf(
                TogetherSeatStrategy.class,
                selector.selectAutomatic(3, true)
        );
    }

    @Test
    void automaticCenterTest() {
        SeatSelector selector = new SeatSelector();

        assertInstanceOf(
                CenterSeatStrategy.class,
                selector.selectAutomatic(1, true)
        );
    }

    @Test
    void automaticFrontTest() {
        SeatSelector selector = new SeatSelector();

        assertInstanceOf(
                FrontSeatStrategy.class,
                selector.selectAutomatic(1, false)
        );
    }

    @Test
    void runtimeSwitchTest() {
        SeatContext context = new SeatContext(
                new FrontSeatStrategy());

        assertArrayEquals(
                new int[]{0},
                context.selectSeats(available, 1)
        );

        context.setStrategy(new CenterSeatStrategy());

        assertArrayEquals(
                new int[]{4},
                context.selectSeats(available, 1)
        );
    }

    static Stream<SeatStrategy> strategies() {
        return Stream.of(
                new FrontSeatStrategy(),
                new CenterSeatStrategy(),
                new TogetherSeatStrategy()
        );
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void selectedSeatsAreAvailable(SeatStrategy strategy) {
        int[] selected = strategy.selectSeats(available, 2);

        assertEquals(2, selected.length);

        for (int seat : selected) {
            assertTrue(available[seat]);
        }
    }

    @Test
    void notEnoughSeatsTest() {
        SeatContext context = new SeatContext(
                new TogetherSeatStrategy());

        assertThrows(
                IllegalStateException.class,
                () -> context.selectSeats(
                        new boolean[]{true, false, true}, 2)
        );
    }
}

