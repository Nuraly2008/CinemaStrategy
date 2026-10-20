# Cinema Ticket Booking System

**Assignment 5 — Strategy Design Pattern**

A Java console application demonstrating the Strategy Design Pattern through cinema ticket pricing and seat selection.

## Technologies

- Java 17
- Maven
- JUnit 5
- Strategy Pattern

## Strategy Families

### 1. Ticket Pricing

| Strategy | Description |
|---|---|
| RegularPricing | Standard ticket price |
| StudentPricing | 20% weekday discount when occupancy is below 80% |
| GroupPricing | Every fourth ticket is free |

### 2. Seat Selection

| Strategy | Description |
|---|---|
| FrontSeatStrategy | Selects the first available seats |
| CenterSeatStrategy | Selects seats closest to the center |
| TogetherSeatStrategy | Selects consecutive available seats |

## Class Responsibilities (SRP)

| Class | Responsibility |
|---|---|
| Main | Demonstrates application functionality |
| PricingStrategy | Defines the pricing contract |
| RegularPricing | Calculates standard prices |
| StudentPricing | Calculates student prices |
| GroupPricing | Calculates group prices |
| PricingContext | Executes the selected pricing strategy |
| PricingSelector | Selects pricing strategies |
| PricingCalculator | Preserves the original pricing API |
| SeatStrategy | Defines the seat selection contract |
| FrontSeatStrategy | Finds the first available seats |
| CenterSeatStrategy | Finds central seats |
| TogetherSeatStrategy | Finds consecutive seats |
| SeatContext | Executes the selected seat strategy |
| SeatSelector | Selects seat strategies |
| DemandSource | Defines the occupancy data contract |
| SimulatedDemandSource | Provides simulated occupancy data |
| PricingTest | Tests original pricing behavior |
| PricingRulesTest | Tests pricing strategies |
| SeatTest | Tests seat selection behavior |

## Why Strategy Pattern?

The Strategy Pattern allows multiple algorithms to implement the same operation through a common interface. In this project, pricing and seat selection have three interchangeable strategies each.

The Context classes delegate work to strategies without knowing their concrete implementations. The `setStrategy()` method allows algorithms to change at runtime.

This follows the **Open/Closed Principle** because new strategies can be added without modifying the Context classes.

**Strategy vs State:** State changes behavior according to an object's internal state. Strategy allows the application to choose independent algorithms.

**Strategy vs Template Method:** Template Method uses inheritance to customize algorithm steps. Strategy uses composition to replace complete algorithms.

Therefore, Strategy is suitable for this project because pricing and seat selection require flexible algorithm selection rather than state transitions.

## External Data Isolation

`StudentPricing` uses the `DemandSource` interface to obtain cinema occupancy data.

`SimulatedDemandSource` returns simulated data. Unit tests use fake data sources with fixed values, making testing independent of external services.

## Testing

JUnit 5 tests cover strategies, Selector rules, runtime switching, fake data sources, and parameterized testing.

| Test Class | Tests |
|---|---:|
| PricingTest | 7 |
| PricingRulesTest | 8 |
| SeatTest | 11 |
| **Total** | **26** |

**Result: BUILD SUCCESS — 26 tests passed.**

## Refactoring History

**BEFORE:** All pricing algorithms were implemented inside `PricingCalculator` using switch-case.

[View BEFORE Commit](https://github.com/Nuraly2008/CinemaStrategy/commit/cc41405)

**AFTER:** Pricing algorithms were separated into classes implementing `PricingStrategy`. The original seven tests still pass.

[View AFTER Commit](https://github.com/Nuraly2008/CinemaStrategy/commit/98cc7f8)

## How to Run

1. Open the project in IntelliJ IDEA.
2. Load Maven dependencies.
3. Run `Main.java`.
4. Run tests using Maven → Lifecycle → test.

## Conclusion

The project demonstrates interchangeable algorithms, runtime switching, automatic strategy selection, external data isolation, and clean class responsibilities.