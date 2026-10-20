
# Cinema Ticket Booking System

## Assignment 5 — Strategy Design Pattern

A Java console application demonstrating the Strategy Design Pattern through cinema ticket pricing and seat selection.

The project follows Lecture 7 concepts: interchangeable algorithms, runtime strategy switching, Open/Closed Principle, Single Responsibility Principle, and isolation from change.

## Technologies

- Java 17+
- Maven
- JUnit 5
- Strategy Design Pattern
- Git / GitHub

## 1. Project Overview

The cinema system supports two independent strategy families.

### Family 1: Ticket Pricing

| Strategy | Description |
|---|---|
| RegularPricing | Calculates the full ticket price. |
| StudentPricing | Applies a 20% weekday discount when occupancy is below 80%. |
| GroupPricing | Provides one free ticket for every four tickets. |

### Family 2: Seat Selection

| Strategy | Description |
|---|---|
| FrontSeatStrategy | Selects the first available seats. |
| CenterSeatStrategy | Selects seats closest to the center. |
| TogetherSeatStrategy | Finds consecutive available seats for a group. |

## 2. Strategy Pattern Structure

Each strategy family contains:

- Strategy Interface
- Three Concrete Strategies
- Context Class
- Selector Class

Pricing:

PricingStrategy
- RegularPricing
- StudentPricing
- GroupPricing

PricingContext stores the selected PricingStrategy.

PricingSelector chooses strategies based on customer information.

Seats:

SeatStrategy
- FrontSeatStrategy
- CenterSeatStrategy
- TogetherSeatStrategy

SeatContext stores the selected SeatStrategy.

SeatSelector chooses strategies based on group size and preferences.

## 3. Class Responsibilities (SRP)

Each class has one responsibility.

| Class | Responsibility |
|---|---|
| Main | Demonstrates the cinema booking functionality. |
| PricingStrategy | Defines the contract for ticket pricing algorithms. |
| RegularPricing | Calculates standard ticket costs. |
| StudentPricing | Calculates demand-dependent student ticket costs. |
| GroupPricing | Calculates group ticket costs using free-ticket rules. |
| PricingContext | Delegates price calculation to the selected strategy. |
| PricingSelector | Selects the appropriate ticket pricing algorithm. |
| PricingCalculator | Provides compatibility with the original pricing API. |
| SeatStrategy | Defines the contract for seat selection algorithms. |
| FrontSeatStrategy | Finds available seats starting from the front. |
| CenterSeatStrategy | Finds available seats closest to the center. |
| TogetherSeatStrategy | Finds consecutive available seats. |
| SeatContext | Delegates seat selection to the selected strategy. |
| SeatSelector | Selects a seat algorithm based on booking preferences. |
| DemandSource | Defines the contract for cinema occupancy data. |
| SimulatedDemandSource | Supplies simulated cinema occupancy information. |
| PricingTest | Verifies the original ticket pricing behavior. |
| PricingRulesTest | Verifies pricing strategies, selection rules, external data usage. |
| SeatTest | Verifies seat strategies, selection rules, runtime switching. |

## 4. Why Strategy Instead of State or Template Method?

This project uses the Strategy Design Pattern because cinema booking operations can be performed using several interchangeable algorithms. The system needs to change pricing rules and seat selection methods without changing the main business logic.

Strategy defines a common interface for each family of algorithms. Every concrete strategy implements its own behavior. The Context class stores a reference to the selected strategy and delegates the operation to it. Using setStrategy(), the application can replace an algorithm during runtime.

This design follows the Open/Closed Principle. A new strategy can be introduced by creating a class implementing the existing interface. The Context class does not require modification. The application can also test each algorithm independently.

The State Pattern has a different purpose. State represents the current internal condition of an object. Behavior changes because the object transitions between states. In this project, strategies represent alternative algorithms selected by the client or Selector. No internal state machine controls those changes.

Template Method also differs from Strategy. Template Method uses inheritance and defines a fixed algorithm structure in a base class. Subclasses override particular steps. Strategy instead uses composition. The Context contains a Strategy object and can replace the complete algorithm at runtime.

Therefore, Strategy is the most suitable pattern for this cinema booking application because the main requirement is selecting interchangeable algorithms while keeping the Context independent of their implementations.

## 5. Isolation from Change

StudentPricing requires cinema occupancy information.

Instead of accessing a concrete data provider directly, it depends on the DemandSource interface.

SimulatedDemandSource provides simulated occupancy data.

JUnit tests use fake implementations returning fixed occupancy values.

This approach isolates external dependencies and supports independent unit testing.

## 6. Automatic Strategy Selection

PricingSelector:
- Four or more tickets: GroupPricing
- Student booking on a weekday: StudentPricing
- Other bookings: RegularPricing

SeatSelector:
- Multiple people: TogetherSeatStrategy
- One person preferring the center: CenterSeatStrategy
- Other single-person bookings: FrontSeatStrategy

## 7. Demo

The Main class demonstrates:

1. Processing identical inputs using different pricing strategies.
2. Manual strategy selection.
3. Automatic strategy selection.
4. Switching strategies at runtime.
5. Three different seat selection algorithms.
6. Simulated external occupancy data.

Example output:

    Regular: 8000.0 KZT
    Student: 6400.0 KZT
    Group: 6000.0 KZT

    Front: [0, 1]
    Center: [4, 5]
    Together: [0, 1]

## 8. Unit Testing

The project uses JUnit 5.

Tests cover:
- Individual pricing strategies
- Individual seat selection strategies
- Selector rules
- Runtime strategy switching
- Invalid input handling
- Fake external data sources
- Parameterized strategy tests

Test classes:

| Test Class | Number of Tests |
|---|---|
| PricingTest | 7 |
| PricingRulesTest | 8 |
| SeatTest | 11 |
| Total | 26 |

Run tests using:

    mvn clean test

## 9. Refactoring History

The project demonstrates refactoring from a switch-based implementation to the Strategy Design Pattern.

### Before Refactoring

Initially, PricingCalculator used a switch statement to implement all pricing algorithms inside a single class.

Commit: cc41405

### After Refactoring

Each pricing algorithm was extracted into a separate class implementing PricingStrategy.

PricingContext delegates calculations to the selected strategy.

PricingSelector handles algorithm selection.

The original seven pricing tests remain unchanged and pass after refactoring.

The GitHub links to both commits are included in the repository history.

## 10. How to Run

1. Open the project in IntelliJ IDEA.
2. Load Maven dependencies.
3. Open Main.java.
4. Run the main() method.
5. Observe pricing and seat selection results.
6. Run JUnit tests.

## Conclusion

The application demonstrates the Strategy Design Pattern using two families of interchangeable algorithms.

The design supports runtime switching, algorithm extension, independent testing, and external dependency isolation.

The implementation follows the principles covered in Lecture 7.
