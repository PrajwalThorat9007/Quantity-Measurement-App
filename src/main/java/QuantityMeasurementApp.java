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

        System.out.println(
                new Quantity<>(1.0, VolumeUnit.MILLILITRE)
                        .convertTo(VolumeUnit.LITRE)
        );

        System.out.println(
                new Quantity<>(1.0, VolumeUnit.GALLON)
                        .convertTo(VolumeUnit.LITRE)
        );

        Quantity<VolumeUnit> volume1 =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> volume2 =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        Quantity<VolumeUnit> volume3 =
                new Quantity<>(1.0, VolumeUnit.GALLON);

        System.out.println("1 L == 1000 mL : "
                + demonstrateEquality(volume1, volume2));

        System.out.println("3.78541 L == 1 Gallon : "
                + demonstrateEquality(
                new Quantity<>(3.78541, VolumeUnit.LITRE),
                volume3
        ));

        System.out.println("1 L → mL : "
                + demonstrateConversion(volume1, VolumeUnit.MILLILITRE));

        System.out.println("1 Gallon → L : "
                + demonstrateConversion(volume3, VolumeUnit.LITRE));

        System.out.println("500 mL → Gallon : "
                + demonstrateConversion(
                new Quantity<>(500.0, VolumeUnit.MILLILITRE),
                VolumeUnit.GALLON));

        System.out.println("1 L + 1000 mL : "
                + demonstrateAddition(volume1, volume2));

        System.out.println("1 L + 1 Gallon in mL : "
                + demonstrateAddition(volume1, volume3, VolumeUnit.MILLILITRE));

        System.out.println("Volume vs Length : "
                + volume1.equals(new Quantity<>(1.0, LengthUnit.FEET)));

        System.out.println("Volume vs Weight : "
                + volume1.equals(new Quantity<>(1.0, WeightUnit.KILOGRAM)));
    }

}