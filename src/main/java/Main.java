
import pricing.*;
import seats.*;
import data.*;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {

        System.out.println("=== CINEMA BOOKING SYSTEM ===");

        double price = 2000;
        int tickets = 4;
        boolean weekday = true;

        System.out.println("\n1. ALL PRICING STRATEGIES");

        PricingContext pricing = new PricingContext(
                new RegularPricing()
        );

        System.out.println("Regular: " +
                pricing.calculate(price, tickets, weekday) + " KZT");

        pricing.setStrategy(new StudentPricing());

        System.out.println("Student: " +
                pricing.calculate(price, tickets, weekday) + " KZT");

        pricing.setStrategy(new GroupPricing());

        System.out.println("Group: " +
                pricing.calculate(price, tickets, weekday) + " KZT");


        System.out.println("\n2. MANUAL VS AUTOMATIC PRICING");

        PricingSelector pricingSelector = new PricingSelector();

        PricingStrategy manual =
                pricingSelector.selectManual("student");

        pricing.setStrategy(manual);

        System.out.println("Manual selection: " +
                pricing.calculate(price, tickets, weekday) + " KZT");

        PricingStrategy automatic =
                pricingSelector.selectAutomatic(true, tickets, weekday);

        pricing.setStrategy(automatic);

        System.out.println("Automatic strategy: " +
                automatic.getClass().getSimpleName());

        System.out.println("Automatic price: " +
                pricing.calculate(price, tickets, weekday) + " KZT");


        System.out.println("\n3. ALL SEAT STRATEGIES");

        boolean[] available = {
                true, true, false, true, true,
                true, false, true, true, true
        };

        SeatContext seats = new SeatContext(
                new FrontSeatStrategy()
        );

        int people = 2;

        System.out.println("Front: " +
                Arrays.toString(seats.selectSeats(available, people)));

        seats.setStrategy(new CenterSeatStrategy());

        System.out.println("Center: " +
                Arrays.toString(seats.selectSeats(available, people)));

        seats.setStrategy(new TogetherSeatStrategy());

        System.out.println("Together: " +
                Arrays.toString(seats.selectSeats(available, people)));


        System.out.println("\n4. AUTOMATIC SEAT SELECTION");

        SeatSelector seatSelector = new SeatSelector();

        SeatStrategy autoSeat =
                seatSelector.selectAutomatic(people, true);

        seats.setStrategy(autoSeat);

        System.out.println("Selected strategy: " +
                autoSeat.getClass().getSimpleName());

        System.out.println("Selected seats: " +
                Arrays.toString(seats.selectSeats(available, people)));


        System.out.println("\n5. EXTERNAL DATA SOURCE");

        DemandSource demandSource = new SimulatedDemandSource();

        System.out.println("Cinema occupancy: " +
                demandSource.getOccupancyPercent() + "%");

        pricing.setStrategy(new StudentPricing(demandSource));

        System.out.println("Student price: " +
                pricing.calculate(price, tickets, weekday) + " KZT");


        System.out.println("\n=== DEMO COMPLETED ===");
    }
}

