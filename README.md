# Quantity-Measurement-App

A progressive Java project built use case by use case, demonstrating core OOP principles,
Java Streams, unit conversion, and JUnit testing. Each use case builds on the previous one
without breaking existing functionality.

---

## Project Structure

```
src/
├── main/java/
│   ├── Feet.java                    ← UC1
│   ├── Inches.java                  ← UC2
│   ├── LengthUnit.java              ← UC3, UC4
│   ├── QuantityLength.java          ← UC3 → UC7
│   └── QuantityMeasurementApp.java  ← UC1 → UC7
└── test/java/
    └── QuantityMeasurementTest.java ← UC1 → UC7
```

---

## Use Case Summary

| UC | Title | Key Addition | Files Changed |
|----|-------|-------------|---------------|
| UC1 | Feet Equality | `Feet` class with `equals()` | `Feet.java` |
| UC2 | Inches Equality | `Inches` class with `equals()` | `Inches.java` |
| UC3 | Generic Quantity (DRY) | Single `QuantityLength` class + `LengthUnit` enum | `LengthUnit.java`, `QuantityLength.java` |
| UC4 | Extended Units | Added `YARDS` and `CENTIMETERS` to enum | `LengthUnit.java` |
| UC5 | Unit Conversion | `convert()` and `convertTo()` methods | `QuantityLength.java` |
| UC6 | Addition (first unit) | `add()` — result in first operand's unit | `QuantityLength.java` |
| UC7 | Addition (explicit unit) | `add(a, b, targetUnit)` — result in any unit | `QuantityLength.java` |

---

## UC1 — Feet Equality

**Goal:** Compare two feet measurements for equality.

**What was built:**
- `Feet` class with a `double value` field
- `equals()` method overriding Object — handles null, same reference, type check, and value comparison
- Uses `Double.compare()` for safe floating point comparison

**Key concept:** Object equality contract — reflexive, symmetric, transitive, null-safe.

```java
Feet feet1 = new Feet(1.0);
Feet feet2 = new Feet(1.0);
feet1.equals(feet2); // true
```

---

## UC2 — Inches Equality

**Goal:** Compare two inches measurements for equality, similar to UC1.

**What was built:**
- `Inches` class — identical structure to `Feet`
- Same `equals()` implementation
- Cross-type comparison: `Feet` and `Inches` with same value are NOT equal (different types)

**Problem identified:** Both `Feet` and `Inches` classes had nearly identical code — violating the DRY principle.

```java
Inches inch1 = new Inches(1.0);
Inches inch2 = new Inches(1.0);
inch1.equals(inch2); // true

new Feet(1.0).equals(new Inches(1.0)); // false — different types
```

---

## UC3 — Generic Quantity Class (DRY Principle)

**Goal:** Eliminate code duplication by replacing `Feet` and `Inches` with a single generic class.

**What was built:**
- `LengthUnit` enum — holds `FEET` and `INCHES` with conversion factors relative to feet (base unit)
- `QuantityLength` class — single class representing any length measurement
- `equals()` compares values by converting both to base unit (feet) first

**Key concepts:** DRY principle, Enum usage, polymorphism, encapsulation, abstraction.

```java
// 1 foot == 12 inches — cross unit equality
new QuantityLength(1.0, LengthUnit.FEET).equals(
new QuantityLength(12.0, LengthUnit.INCHES)); // true
```

**Conversion formula:**
```
toBaseUnit() = value × conversionFactor
FEET   → 1.0 × 1.0    = 1.0 feet
INCHES → 12.0 × 0.0833 = 1.0 feet  → EQUAL
```

---

## UC4 — Extended Unit Support

**Goal:** Add `YARDS` and `CENTIMETERS` without changing `QuantityLength` — proving UC3 design scales.

**What was built:**
- Added `YARDS(3.0)` — 1 yard = 3 feet
- Added `CENTIMETERS(0.393701 / 12.0)` — 1 cm = 0.393701 inches = 0.393701/12 feet
- Zero changes to `QuantityLength.java`

**Key concept:** Enum extensibility — adding a unit = one line in the enum, no new class needed.

```java
new QuantityLength(1.0, LengthUnit.YARDS).equals(
new QuantityLength(3.0, LengthUnit.FEET));   // true

new QuantityLength(1.0, LengthUnit.YARDS).equals(
new QuantityLength(36.0, LengthUnit.INCHES)); // true
```

---

## UC5 — Unit-to-Unit Conversion

**Goal:** Expose explicit conversion operations beyond equality checks.

**What was built:**
- `static convert(value, sourceUnit, targetUnit)` — returns converted numeric value
- `convertTo(targetUnit)` — instance method returning new `QuantityLength`
- `toString()` override — human-readable representation
- Input validation — rejects `null` units, `NaN`, and infinite values
- Overloaded `demonstrateLengthConversion()` in app — raw value version and instance version

**Key concepts:** Method overloading, immutability, value object semantics, JavaDoc.

```java
QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES); // 12.0
QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES); // 36.0

new QuantityLength(1.0, LengthUnit.YARDS).convertTo(LengthUnit.FEET);
// returns new QuantityLength(3.0, FEET)
```

**Conversion formula:**
```
result = value × (sourceUnit.factor / targetUnit.factor)
       = baseValue / targetUnit.factor
```

---

## UC6 — Addition of Two Lengths (First Operand Unit)

**Goal:** Add two length measurements, returning result in the unit of the first operand.

**What was built:**
- Instance `add(QuantityLength other)` — result in `this` object's unit
- Static `add(first, second, targetUnit)` — result in explicit unit (foundation for UC7)
- Immutability preserved — original objects unchanged after addition

**Key concepts:** Arithmetic on value objects, normalization to base unit, immutability, commutativity.

```java
// 1 foot + 12 inches = 2 feet (result in first operand unit)
new QuantityLength(1.0, LengthUnit.FEET)
    .add(new QuantityLength(12.0, LengthUnit.INCHES));
// → QuantityLength(2.0, FEET)

// 12 inches + 1 foot = 24 inches (result in first operand unit)
new QuantityLength(12.0, LengthUnit.INCHES)
    .add(new QuantityLength(1.0, LengthUnit.FEET));
// → QuantityLength(24.0, INCHES)
```

**Addition steps:**
```
1. convert both to base unit (feet)
2. add base values
3. convert sum back to first operand's unit
```

---

## UC7 — Addition with Explicit Target Unit

**Goal:** Allow caller to specify any unit for the addition result, not just the first operand's unit.

**What was built:**
- Overloaded `add(other, targetUnit)` instance method
- Overloaded `static add(first, second, targetUnit)` static method
- Private helper `addInBaseUnit()` — shared logic used by all `add()` overloads, avoiding duplication
- Backward compatible with UC6 `add()` (no target unit = default to first operand unit)

**Key concepts:** Method overloading, private utility method, DRY, API design, caller intent clarity.

```java
QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

QuantityLength.add(a, b, LengthUnit.FEET);    // → 2.0 FEET
QuantityLength.add(a, b, LengthUnit.INCHES);  // → 24.0 INCHES
QuantityLength.add(a, b, LengthUnit.YARDS);   // → 0.67 YARDS
```

---

## Concepts Learned Across All Use Cases

| Concept | Where Introduced |
|---------|-----------------|
| Object equality contract | UC1, UC2 |
| DRY Principle | UC3 |
| Enum with behavior | UC3, UC4 |
| Polymorphism | UC3 |
| Encapsulation & Abstraction | UC3 |
| Enum extensibility | UC4 |
| Method overloading | UC5, UC6, UC7 |
| Immutability | UC5, UC6, UC7 |
| Value object semantics | UC5 |
| Input validation | UC5, UC6, UC7 |
| Floating point comparison with epsilon | UC5, UC6, UC7 |
| Private helper methods | UC7 |
| Backward compatibility | UC3 → UC7 |
| JUnit testing (happy + sad) | UC1 → UC7 |
| GitFlow branching | All UCs |

---

## JUnit Test Coverage

Each use case has both happy and sad test cases:

| Test Category | Examples |
|--------------|---------|
| Same value equality | `testEquality_SameValue()` |
| Different value equality | `testEquality_DifferentValue()` |
| Null comparison | `testEquality_NullComparison()` |
| Same reference | `testEquality_SameReference()` |
| Cross unit equality | `testEquality_FeetToInch_EquivalentValue()` |
| Unit conversion | `testConversion_FeetToInches()` |
| Round trip conversion | `testConversion_RoundTrip_PreservesValue()` |
| Addition same unit | `testAddition_SameUnit_FeetPlusFeet()` |
| Addition cross unit | `testAddition_CrossUnit_FeetPlusInches()` |
| Addition explicit target | `testAddition_ExplicitTargetUnit_Yards()` |
| Null/invalid input | `testConversion_NullSourceUnit_Throws()` |
| Commutativity | `testAddition_Commutativity()` |
| Immutability | `testAddition_OriginalUnchanged()` |
| Backward compatibility | `testBackwardCompatibility_UC6_AddDefaultUnit()` |

---

## Design Evolution

```
UC1 → Feet class alone              → compare feet to feet
UC2 → Feet + Inches (duplicate)     → DRY violated
UC3 → One QuantityLength class      → DRY restored, cross unit equality
UC4 → Yards + Centimeters in enum   → zero class changes needed
UC5 → Conversion methods added      → explicit unit conversion
UC6 → Addition (first unit)         → arithmetic on value objects
UC7 → Addition (explicit unit)      → full flexibility, private helper
```

Every use case extended the previous one. No existing functionality was broken at any stage.

---

## How to Run

**Prerequisites:** Java 11+, Maven

```bash
# clone the repo
git clone https://github.com/PrajwalThorat9007/Lambda_Expression.git
cd Lambda_Expression

# run all tests
mvn test

# run a specific test
mvn test -Dtest=QuantityMeasurementTest

# compile and run the app
mvn compile exec:java -Dexec.mainClass="QuantityMeasurementApp"
```

---

## Branch Strategy (GitFlow)

```
main      ← README only
develop   ← stable merged code
feature/UC1  ← feet equality
feature/UC2  ← inches equality
feature/UC3  ← generic quantity class
feature/UC4  ← yards and centimeters
feature/UC5  ← unit conversion
feature/UC6  ← addition (first unit)
feature/UC7  ← addition (explicit unit)
```

Each feature branch was merged into `develop` via Pull Request after completion.
