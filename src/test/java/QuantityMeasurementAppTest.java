import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // epsilon for floating point comparison
    private static final double EPSILON = 1e-6;

    // ── Basic Conversion ─────────────────────────────────────────────────

    @Test
    void testConversion_FeetToInches() {
        // 1 foot = 12 inches
        assertEquals(12.0, QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_InchesToFeet() {
        // 24 inches = 2 feet
        assertEquals(2.0, QuantityLength.convert(24.0, LengthUnit.INCHES, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversion_YardsToFeet() {
        // 3 yards = 9 feet
        assertEquals(9.0, QuantityLength.convert(3.0, LengthUnit.YARDS, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversion_FeetToYards() {
        // 6 feet = 2 yards
        assertEquals(2.0, QuantityLength.convert(6.0, LengthUnit.FEET, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversion_YardsToInches() {
        // 1 yard = 36 inches
        assertEquals(36.0, QuantityLength.convert(1.0, LengthUnit.YARDS, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_InchesToYards() {
        // 72 inches = 2 yards
        assertEquals(2.0, QuantityLength.convert(72.0, LengthUnit.INCHES, LengthUnit.YARDS), EPSILON);
    }

    @Test
    void testConversion_CentimetersToInches() {
        // 2.54 cm ≈ 1.0 inch
        assertEquals(1.0, QuantityLength.convert(2.54, LengthUnit.CENTIMETERS, LengthUnit.INCHES), 1e-4);
    }

    @Test
    void testConversion_InchesToCentimeters() {
        // 1 inch ≈ 2.54 cm
        assertEquals(2.54, QuantityLength.convert(1.0, LengthUnit.INCHES, LengthUnit.CENTIMETERS), 1e-4);
    }

    // ── Same Unit Conversion ─────────────────────────────────────────────

    @Test
    void testConversion_SameUnit_Feet() {
        // converting feet to feet returns same value
        assertEquals(5.0, QuantityLength.convert(5.0, LengthUnit.FEET, LengthUnit.FEET), EPSILON);
    }

    @Test
    void testConversion_SameUnit_Inches() {
        // converting inches to inches returns same value
        assertEquals(10.0, QuantityLength.convert(10.0, LengthUnit.INCHES, LengthUnit.INCHES), EPSILON);
    }

    // ── Zero and Negative Values ─────────────────────────────────────────

    @Test
    void testConversion_ZeroValue() {
        // zero in any unit = zero in target unit
        assertEquals(0.0, QuantityLength.convert(0.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_NegativeValue() {
        // -1 foot = -12 inches
        assertEquals(-12.0, QuantityLength.convert(-1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_LargeValue() {
        // 1000 feet = 12000 inches
        assertEquals(12000.0, QuantityLength.convert(1000.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testConversion_SmallValue() {
        // 0.001 feet converts correctly
        assertEquals(0.012, QuantityLength.convert(0.001, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    // ── Round Trip Conversion ────────────────────────────────────────────

    @Test
    void testConversion_RoundTrip_FeetToInchesBack() {
        // feet → inches → feet should give original value
        double original  = 5.0;
        double toInches  = QuantityLength.convert(original, LengthUnit.FEET, LengthUnit.INCHES);
        double backToFeet = QuantityLength.convert(toInches, LengthUnit.INCHES, LengthUnit.FEET);
        assertEquals(original, backToFeet, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_YardsToInchesBack() {
        // yards → inches → yards should give original value
        double original  = 3.0;
        double toInches  = QuantityLength.convert(original, LengthUnit.YARDS, LengthUnit.INCHES);
        double backToYards = QuantityLength.convert(toInches, LengthUnit.INCHES, LengthUnit.YARDS);
        assertEquals(original, backToYards, EPSILON);
    }

    @Test
    void testConversion_RoundTrip_ThreeSteps() {
        // feet → inches → yards → feet should preserve value
        double original = 6.0;
        double step1 = QuantityLength.convert(original, LengthUnit.FEET,   LengthUnit.INCHES);
        double step2 = QuantityLength.convert(step1,    LengthUnit.INCHES,  LengthUnit.YARDS);
        double step3 = QuantityLength.convert(step2,    LengthUnit.YARDS,   LengthUnit.FEET);
        assertEquals(original, step3, EPSILON);
    }

    // ── Instance convertTo() method ──────────────────────────────────────

    @Test
    void testConvertTo_FeetToInches() {
        // instance convertTo should return new QuantityLength in target unit
        QuantityLength oneFoot   = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength inInches  = oneFoot.convertTo(LengthUnit.INCHES);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), inInches);
    }

    @Test
    void testConvertTo_YardToFeet() {
        // 1 yard converted to feet = 3 feet
        QuantityLength oneYard  = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength inFeet   = oneYard.convertTo(LengthUnit.FEET);
        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), inFeet);
    }

    // ── Invalid Input Handling ───────────────────────────────────────────

    @Test
    void testConversion_NullSourceUnit_Throws() {
        // null source unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(1.0, null, LengthUnit.INCHES);
        });
    }

    @Test
    void testConversion_NullTargetUnit_Throws() {
        // null target unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(1.0, LengthUnit.FEET, null);
        });
    }

    @Test
    void testConversion_NaNValue_Throws() {
        // NaN value should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(Double.NaN, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    void testConversion_InfiniteValue_Throws() {
        // infinite value should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            QuantityLength.convert(Double.POSITIVE_INFINITY, LengthUnit.FEET, LengthUnit.INCHES);
        });
    }

    @Test
    void testConvertTo_NullTargetUnit_Throws() {
        // convertTo with null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, LengthUnit.FEET).convertTo(null);
        });
    }

    // ── toString ────────────────────────────────────────────────────────

    @Test
    void testToString_Format() {
        // toString should return value and unit name
        QuantityLength feet = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals("1.000000 FEET", feet.toString());
    }

    // ── Backward Compatibility ───────────────────────────────────────────

    @Test
    void testBackwardCompatibility_FeetEquality() {
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testBackwardCompatibility_CrossUnit() {
        // 1 yard == 3 feet still works
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(oneYard, threeFeet);
    }
}