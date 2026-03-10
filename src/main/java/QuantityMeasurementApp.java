public class QuantityMeasurementApp {

    // ── Generic Equality Demonstration ─────────────────────────────

    public static <U extends IMeasurable> boolean demonstrateEquality(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.equals(quantity2);
    }

    // ── Generic Conversion Demonstration ───────────────────────────

    public static <U extends IMeasurable> Quantity<U> demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit) {

        return quantity.convertTo(targetUnit);
    }

    // ── Generic Addition (result in first operand unit) ────────────

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2) {

        return quantity1.add(quantity2);
    }

    // ── Generic Addition (explicit target unit) ────────────────────

    public static <U extends IMeasurable> Quantity<U> demonstrateAddition(
            Quantity<U> quantity1,
            Quantity<U> quantity2,
            U targetUnit) {

        return quantity1.add(quantity2, targetUnit);
    }

    // ── Main Method Demonstration ──────────────────────────────────

    public static void main(String[] args) {

        System.out.println("=== Length Operations ===");

        Quantity<LengthUnit> lengthFeet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> lengthInches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        System.out.println("Length Equality: " +
                demonstrateEquality(lengthFeet, lengthInches));

        System.out.println("Length Conversion (Feet → Inches): " +
                demonstrateConversion(lengthFeet, LengthUnit.INCHES));

        System.out.println("Length Addition: " +
                demonstrateAddition(lengthFeet, lengthInches));


        System.out.println("\n=== Weight Operations ===");

        Quantity<WeightUnit> weightKg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> weightGram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        System.out.println("Weight Equality: " +
                demonstrateEquality(weightKg, weightGram));

        System.out.println("Weight Conversion (Kg → Gram): " +
                demonstrateConversion(weightKg, WeightUnit.GRAM));

        System.out.println("Weight Addition: " +
                demonstrateAddition(weightKg, weightGram));


        System.out.println("\n=== Explicit Target Unit Addition ===");

        Quantity<WeightUnit> weightPound =
                new Quantity<>(2.0, WeightUnit.POUND);

        System.out.println("Addition in KG: " +
                demonstrateAddition(weightKg, weightPound, WeightUnit.KILOGRAM));
    }
}