public class QuantityMeasurementApp {

    // ── Weight Demo Methods ──────────────────────────────────────────────

    /**
     * Demonstrates weight equality.
     */
    public static void demonstrateWeightEquality(QuantityWeight a, QuantityWeight b) {
        System.out.printf("equals(%s, %s) = %b%n", a, b, a.equals(b));
    }

    /**
     * Demonstrates weight conversion using instance method.
     */
    public static void demonstrateWeightConversion(QuantityWeight weight, WeightUnit toUnit) {
        QuantityWeight result = weight.convertTo(toUnit);
        System.out.printf("convert(%s → %s) = %s%n", weight, toUnit, result);
    }

    /**
     * Demonstrates weight addition with result in first operand unit.
     */
    public static void demonstrateWeightAddition(QuantityWeight first, QuantityWeight second) {
        QuantityWeight result = first.add(second);
        System.out.printf("add(%s, %s) = %s%n", first, second, result);
    }

    /**
     * Demonstrates weight addition with explicit target unit.
     */
    public static void demonstrateWeightAddition(QuantityWeight first,
                                                 QuantityWeight second,
                                                 WeightUnit targetUnit) {
        QuantityWeight result = QuantityWeight.add(first, second, targetUnit);
        System.out.printf("add(%s, %s, %s) = %s%n", first, second, targetUnit, result);
    }

    // ── Length Demo Methods (unchanged from UC8) ─────────────────────────

    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        System.out.printf("convert(%.4f %s → %s) = %.6f%n",
                value, from, to, QuantityLength.convert(value, from, to));
    }

    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit toUnit) {
        System.out.printf("convert(%s → %s) = %s%n", length, toUnit, length.convertTo(toUnit));
    }

    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second) {
        System.out.printf("add(%s, %s) = %s%n", first, second, first.add(second));
    }

    public static void demonstrateLengthAddition(QuantityLength first,
                                                 QuantityLength second,
                                                 LengthUnit targetUnit) {
        System.out.printf("add(%s, %s, %s) = %s%n",
                first, second, targetUnit, QuantityLength.add(first, second, targetUnit));
    }

    public static void main(String[] args) {

        System.out.println("=== UC9: Weight Equality ===\n");

        demonstrateWeightEquality(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM));

        demonstrateWeightEquality(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM));

        demonstrateWeightEquality(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND));

        System.out.println("\n=== UC9: Weight Conversion ===\n");

        demonstrateWeightConversion(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM), WeightUnit.GRAM);

        demonstrateWeightConversion(
                new QuantityWeight(2.0, WeightUnit.POUND), WeightUnit.KILOGRAM);

        demonstrateWeightConversion(
                new QuantityWeight(500.0, WeightUnit.GRAM), WeightUnit.POUND);

        System.out.println("\n=== UC9: Weight Addition ===\n");

        // implicit target unit
        demonstrateWeightAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM));

        demonstrateWeightAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM));

        // explicit target unit
        demonstrateWeightAddition(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM);

        demonstrateWeightAddition(
                new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                new QuantityWeight(4.0, WeightUnit.POUND),
                WeightUnit.KILOGRAM);
    }
}