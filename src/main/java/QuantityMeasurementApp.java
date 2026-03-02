public class QuantityMeasurementApp {

    /**
     * Overload 1 - demonstrate conversion using raw value and units.
     * @param value    numeric value
     * @param fromUnit source unit
     * @param toUnit   target unit
     */
    public static void demonstrateLengthConversion(double value, LengthUnit fromUnit, LengthUnit toUnit) {
        double result = QuantityLength.convert(value, fromUnit, toUnit);
        System.out.printf("convert(%.4f %s → %s) = %.6f%n", value, fromUnit, toUnit, result);
    }

    /**
     * Overload 2 - demonstrate conversion using existing QuantityLength object.
     * @param length   existing QuantityLength instance
     * @param toUnit   target unit
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
        QuantityLength a = new QuantityLength(v1, u1);
        QuantityLength b = new QuantityLength(v2, u2);
        demonstrateLengthEquality(a, b);
    }

    public static void main(String[] args) {

        System.out.println("=== Conversion Demo ===\n");

        // overload 1 - raw value conversion
        demonstrateLengthConversion(1.0, LengthUnit.FEET,        LengthUnit.INCHES);
        demonstrateLengthConversion(3.0, LengthUnit.YARDS,        LengthUnit.FEET);
        demonstrateLengthConversion(36.0, LengthUnit.INCHES,      LengthUnit.YARDS);
        demonstrateLengthConversion(1.0, LengthUnit.CENTIMETERS,  LengthUnit.INCHES);
        demonstrateLengthConversion(0.0, LengthUnit.FEET,         LengthUnit.INCHES);

        System.out.println();

        // overload 2 - instance based conversion
        QuantityLength lengthInYards = new QuantityLength(1.0, LengthUnit.YARDS);
        demonstrateLengthConversion(lengthInYards, LengthUnit.INCHES);
        demonstrateLengthConversion(lengthInYards, LengthUnit.FEET);

        System.out.println("\n=== Equality Demo ===\n");

        demonstrateLengthComparison(1.0, LengthUnit.FEET,   12.0, LengthUnit.INCHES);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS,   3.0, LengthUnit.FEET);
        demonstrateLengthComparison(1.0, LengthUnit.YARDS,  36.0, LengthUnit.INCHES);
    }
}