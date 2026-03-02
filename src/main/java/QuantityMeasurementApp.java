public class QuantityMeasurementApp {

    /**
     * Demonstrates addition of two lengths, result in unit of first operand.
     * @param first  first QuantityLength
     * @param second second QuantityLength
     */
    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second) {
        QuantityLength result = first.add(second);
        System.out.printf("add(%s, %s) = %s%n", first, second, result);
    }

    /**
     * Demonstrates addition using raw values and units.
     * @param v1 first value
     * @param u1 first unit
     * @param v2 second value
     * @param u2 second unit
     */
    public static void demonstrateLengthAddition(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        QuantityLength first  = new QuantityLength(v1, u1);
        QuantityLength second = new QuantityLength(v2, u2);
        demonstrateLengthAddition(first, second);
    }

    /**
     * Demonstrates conversion using raw value and units.
     */
    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = QuantityLength.convert(value, fromUnit, toUnit);
        System.out.printf("convert(%.4f %s → %s) = %.6f%n", value, fromUnit, toUnit, result);
    }

    /**
     * Demonstrates conversion using existing QuantityLength object.
     */
    public static void demonstrateLengthConversion(QuantityLength length, LengthUnit toUnit) {
        QuantityLength result = length.convertTo(toUnit);
        System.out.printf("convert(%s → %s) = %s%n", length, toUnit, result);
    }

    /**
     * Demonstrates equality between two lengths.
     */
    public static void demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
        System.out.printf("equals(%s, %s) = %b%n", a, b, a.equals(b));
    }

    /**
     * Demonstrates comparison using raw values and units.
     */
    public static void demonstrateLengthComparison(double v1, LengthUnit u1, double v2, LengthUnit u2) {
        demonstrateLengthEquality(new QuantityLength(v1, u1), new QuantityLength(v2, u2));
    }

    public static void main(String[] args) {

        System.out.println("=== Addition Demo ===\n");

        // same unit addition
        demonstrateLengthAddition(1.0, LengthUnit.FEET,        2.0, LengthUnit.FEET);

        // cross unit addition - result in first operand unit
        demonstrateLengthAddition(1.0, LengthUnit.FEET,       12.0, LengthUnit.INCHES);
        demonstrateLengthAddition(12.0, LengthUnit.INCHES,     1.0, LengthUnit.FEET);
        demonstrateLengthAddition(1.0, LengthUnit.YARDS,       3.0, LengthUnit.FEET);
        demonstrateLengthAddition(36.0, LengthUnit.INCHES,     1.0, LengthUnit.YARDS);
        demonstrateLengthAddition(2.54, LengthUnit.CENTIMETERS, 1.0, LengthUnit.INCHES);

        // zero and negative
        demonstrateLengthAddition(5.0, LengthUnit.FEET,        0.0, LengthUnit.INCHES);
        demonstrateLengthAddition(5.0, LengthUnit.FEET,       -2.0, LengthUnit.FEET);
    }
}