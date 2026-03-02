public class QuantityMeasurementApp {

    /**
     * UC7 - demonstrates addition with explicit target unit using instances.
     */
    public static void demonstrateLengthAddition(QuantityLength first,
                                                 QuantityLength second,
                                                 LengthUnit targetUnit) {
        QuantityLength result = QuantityLength.add(first, second, targetUnit);
        System.out.printf("add(%s, %s, %s) = %s%n", first, second, targetUnit, result);
    }

    /**
     * UC6 - demonstrates addition with result in first operand unit.
     */
    public static void demonstrateLengthAddition(QuantityLength first, QuantityLength second) {
        QuantityLength result = first.add(second);
        System.out.printf("add(%s, %s) = %s%n", first, second, result);
    }

    /**
     * UC6 - raw value overload.
     */
    public static void demonstrateLengthAddition(double v1, LengthUnit u1,
                                                 double v2, LengthUnit u2) {
        demonstrateLengthAddition(new QuantityLength(v1, u1), new QuantityLength(v2, u2));
    }

    /**
     * Demonstrates conversion using raw values.
     */
    public static void demonstrateLengthConversion(double value, LengthUnit from, LengthUnit to) {
        System.out.printf("convert(%.4f %s → %s) = %.6f%n",
                value, from, to, QuantityLength.convert(value, from, to));
    }

    /**
     * Demonstrates equality between two lengths.
     */
    public static void demonstrateLengthEquality(QuantityLength a, QuantityLength b) {
        System.out.printf("equals(%s, %s) = %b%n", a, b, a.equals(b));
    }

    public static void main(String[] args) {

        System.out.println("=== UC7: Addition with Explicit Target Unit ===\n");

        QuantityLength oneFoot    = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInch = new QuantityLength(12.0, LengthUnit.INCHES);

        // same addition, three different target units
        demonstrateLengthAddition(oneFoot, twelveInch, LengthUnit.FEET);
        demonstrateLengthAddition(oneFoot, twelveInch, LengthUnit.INCHES);
        demonstrateLengthAddition(oneFoot, twelveInch, LengthUnit.YARDS);

        System.out.println();

        // yards and feet → result in various units
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        demonstrateLengthAddition(oneYard, threeFeet, LengthUnit.YARDS);
        demonstrateLengthAddition(oneYard, threeFeet, LengthUnit.FEET);
        demonstrateLengthAddition(oneYard, threeFeet, LengthUnit.INCHES);

        System.out.println();

        // centimeters
        QuantityLength twoCm  = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength oneInch = new QuantityLength(1.0, LengthUnit.INCHES);
        demonstrateLengthAddition(twoCm, oneInch, LengthUnit.CENTIMETERS);

        System.out.println();

        // zero and negative
        demonstrateLengthAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS);

        demonstrateLengthAddition(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);
    }
}