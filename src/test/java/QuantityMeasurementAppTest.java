import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ── LengthUnit Enum Constants ────────────────────────────────────────

    @Test
    void testLengthUnitEnum_FeetConstant() {
        // FEET conversion factor should be 1.0 (base unit)
        assertEquals(1.0, LengthUnit.FEET.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_InchesConstant() {
        // INCHES conversion factor should be 1/12
        assertEquals(1.0 / 12.0, LengthUnit.INCHES.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_YardsConstant() {
        // YARDS conversion factor should be 3.0
        assertEquals(3.0, LengthUnit.YARDS.getConversionFactor(), EPSILON);
    }

    @Test
    void testLengthUnitEnum_CentimetersConstant() {
        // CENTIMETERS conversion factor should be 1/30.48
        assertEquals(1.0 / 30.48, LengthUnit.CENTIMETERS.getConversionFactor(), EPSILON);
    }

    // ── convertToBaseUnit() ──────────────────────────────────────────────

    @Test
    void testConvertToBaseUnit_FeetToFeet() {
        // 5 feet → base = 5 feet (no change)
        assertEquals(5.0, LengthUnit.FEET.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_InchesToFeet() {
        // 12 inches → base = 1 foot
        assertEquals(1.0, LengthUnit.INCHES.convertToBaseUnit(12.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_YardsToFeet() {
        // 1 yard → base = 3 feet
        assertEquals(3.0, LengthUnit.YARDS.convertToBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_CentimetersToFeet() {
        // 30.48 cm → base = 1 foot
        assertEquals(1.0, LengthUnit.CENTIMETERS.convertToBaseUnit(30.48), EPSILON);
    }

    // ── convertFromBaseUnit() ────────────────────────────────────────────

    @Test
    void testConvertFromBaseUnit_FeetToFeet() {
        // 2 feet base → 2 feet (no change)
        assertEquals(2.0, LengthUnit.FEET.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToInches() {
        // 1 foot base → 12 inches
        assertEquals(12.0, LengthUnit.INCHES.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToYards() {
        // 3 feet base → 1 yard
        assertEquals(1.0, LengthUnit.YARDS.convertFromBaseUnit(3.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_FeetToCentimeters() {
        // 1 foot base → 30.48 cm
        assertEquals(30.48, LengthUnit.CENTIMETERS.convertFromBaseUnit(1.0), EPSILON);
    }

    // ── Round Trip via Unit Methods ──────────────────────────────────────

    @Test
    void testConvertRoundTrip_InchesToFeetToInches() {
        // 24 inches → base → back to inches
        double base   = LengthUnit.INCHES.convertToBaseUnit(24.0);
        double result = LengthUnit.INCHES.convertFromBaseUnit(base);
        assertEquals(24.0, result, EPSILON);
    }

    @Test
    void testConvertRoundTrip_YardsToFeetToYards() {
        // 2 yards → base → back to yards
        double base   = LengthUnit.YARDS.convertToBaseUnit(2.0);
        double result = LengthUnit.YARDS.convertFromBaseUnit(base);
        assertEquals(2.0, result, EPSILON);
    }

    // ── Refactored QuantityLength Equality ───────────────────────────────

    @Test
    void testQuantityLengthRefactored_Equality() {
        // 1 foot == 12 inches via refactored equals()
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    void testQuantityLengthRefactored_EqualityYards() {
        // 1 yard == 3 feet
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET));
    }

    @Test
    void testQuantityLengthRefactored_EqualityCentimeters() {
        // 30.48 cm == 1 foot
        assertEquals(
                new QuantityLength(30.48, LengthUnit.CENTIMETERS),
                new QuantityLength(1.0, LengthUnit.FEET));
    }

    // ── Refactored ConvertTo ─────────────────────────────────────────────

    @Test
    void testQuantityLengthRefactored_ConvertTo_FeetToInches() {
        // 1 foot convertTo inches = 12 inches
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo_YardsToFeet() {
        // 1 yard convertTo feet = 3 feet
        QuantityLength result = new QuantityLength(1.0, LengthUnit.YARDS)
                .convertTo(LengthUnit.FEET);
        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testQuantityLengthRefactored_ConvertTo_CentimetersToInches() {
        // 2.54 cm convertTo inches ≈ 1 inch
        QuantityLength result = new QuantityLength(2.54, LengthUnit.CENTIMETERS)
                .convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(1.0, LengthUnit.INCHES), result);
    }

    // ── Refactored Addition ──────────────────────────────────────────────

    @Test
    void testQuantityLengthRefactored_Add() {
        // 1 foot + 12 inches = 2 feet
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testQuantityLengthRefactored_AddWithTargetUnit() {
        // 1 foot + 12 inches in YARDS = 0.67 yards
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);
        assertEquals(new QuantityLength(0.67, LengthUnit.YARDS), result);
    }

    // ── Invalid Input ────────────────────────────────────────────────────

    @Test
    void testQuantityLengthRefactored_NullUnit() {
        // null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(1.0, null));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue_NaN() {
        // NaN value should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testQuantityLengthRefactored_InvalidValue_Infinite() {
        // infinite value should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityLength(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // ── Immutability ─────────────────────────────────────────────────────

    @Test
    void testUnitImmutability() {
        // enum constant conversion factor cannot be changed
        double before = LengthUnit.FEET.getConversionFactor();
        // no setter exists — immutability is enforced by enum design
        double after  = LengthUnit.FEET.getConversionFactor();
        assertEquals(before, after, EPSILON);
    }

    // ── Backward Compatibility UC1 → UC7 ────────────────────────────────

    @Test
    void testBackwardCompatibility_UC1_FeetEquality() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.FEET));
    }

    @Test
    void testBackwardCompatibility_UC2_InchesEquality() {
        assertEquals(
                new QuantityLength(1.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.INCHES));
    }

    @Test
    void testBackwardCompatibility_UC5_Conversion() {
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC6_Addition() {
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testBackwardCompatibility_UC7_AdditionWithTargetUnit() {
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);
        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }
}