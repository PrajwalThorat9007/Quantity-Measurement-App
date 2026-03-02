import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ── Yard to Yard ─────────────────────────────────────────────────────

    @Test
    void testEquality_YardToYard_SameValue() {
        // same yard value should be equal
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength yard2 = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(yard1, yard2);
    }

    @Test
    void testEquality_YardToYard_DifferentValue() {
        // different yard values should not be equal
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength yard2 = new QuantityLength(2.0, LengthUnit.YARDS);
        assertNotEquals(yard1, yard2);
    }

    // ── Yard Cross Unit ──────────────────────────────────────────────────

    @Test
    void testEquality_YardToFeet_EquivalentValue() {
        // 1 yard == 3 feet
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(oneYard, threeFeet);
    }

    @Test
    void testEquality_FeetToYard_EquivalentValue() {
        // 3 feet == 1 yard (symmetry check)
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(threeFeet, oneYard);
    }

    @Test
    void testEquality_YardToInches_EquivalentValue() {
        // 1 yard == 36 inches
        QuantityLength oneYard      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength thirtySixIn  = new QuantityLength(36.0, LengthUnit.INCHES);
        assertEquals(oneYard, thirtySixIn);
    }

    @Test
    void testEquality_InchesToYard_EquivalentValue() {
        // 36 inches == 1 yard (symmetry check)
        QuantityLength thirtySixIn = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength oneYard     = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(thirtySixIn, oneYard);
    }

    @Test
    void testEquality_YardToFeet_NonEquivalentValue() {
        // 1 yard != 2 feet
        QuantityLength oneYard  = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength twoFeet  = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(oneYard, twoFeet);
    }

    // ── Centimeters to Centimeters ───────────────────────────────────────

    @Test
    void testEquality_CentimetersToCentimeters_SameValue() {
        // same cm value should be equal
        QuantityLength cm1 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        assertEquals(cm1, cm2);
    }

    @Test
    void testEquality_CentimetersToCentimeters_DifferentValue() {
        // different cm values should not be equal
        QuantityLength cm1 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        assertNotEquals(cm1, cm2);
    }

    // ── Centimeters Cross Unit ───────────────────────────────────────────

    @Test
    void testEquality_CentimetersToInches_EquivalentValue() {
        // 1 cm == 0.393701 inches
        QuantityLength oneCm       = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength pointThreeIn = new QuantityLength(0.393701, LengthUnit.INCHES);
        assertEquals(oneCm, pointThreeIn);
    }

    @Test
    void testEquality_InchesToCentimeters_EquivalentValue() {
        // 0.393701 inches == 1 cm (symmetry check)
        QuantityLength pointThreeIn = new QuantityLength(0.393701, LengthUnit.INCHES);
        QuantityLength oneCm        = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        assertEquals(pointThreeIn, oneCm);
    }

    @Test
    void testEquality_CentimetersToFeet_NonEquivalentValue() {
        // 1 cm != 1 feet
        QuantityLength oneCm   = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(oneCm, oneFoot);
    }

    // ── Transitive and Complex Scenarios ────────────────────────────────

    @Test
    void testEquality_MultiUnit_TransitiveProperty() {
        // 1 yard == 3 feet == 36 inches
        QuantityLength oneYard     = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet   = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength thirtySixIn = new QuantityLength(36.0, LengthUnit.INCHES);
        assertEquals(oneYard, threeFeet);
        assertEquals(threeFeet, thirtySixIn);
        assertEquals(oneYard, thirtySixIn);
    }

    @Test
    void testEquality_AllUnits_ComplexScenario() {
        // 2 yards == 6 feet == 72 inches
        QuantityLength twoYards    = new QuantityLength(2.0, LengthUnit.YARDS);
        QuantityLength sixFeet     = new QuantityLength(6.0, LengthUnit.FEET);
        QuantityLength seventyTwoIn = new QuantityLength(72.0, LengthUnit.INCHES);
        assertEquals(twoYards, sixFeet);
        assertEquals(sixFeet, seventyTwoIn);
        assertEquals(twoYards, seventyTwoIn);
    }

    // ── Null and Reference Safety ────────────────────────────────────────

    @Test
    void testEquality_YardSameReference() {
        // reflexive - yard object must equal itself
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARDS);
        assertEquals(yard1, yard1);
    }

    @Test
    void testEquality_YardNullComparison() {
        // yard compared to null should not be equal
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARDS);
        assertNotEquals(yard1, null);
    }

    @Test
    void testEquality_YardWithNullUnit() {
        // null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    @Test
    void testEquality_CentimetersSameReference() {
        // reflexive - cm object must equal itself
        QuantityLength cm1 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        assertEquals(cm1, cm1);
    }

    @Test
    void testEquality_CentimetersNullComparison() {
        // cm compared to null should not be equal
        QuantityLength cm1 = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        assertNotEquals(cm1, null);
    }

    @Test
    void testEquality_CentimetersWithNullUnit() {
        // null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    // ── Backward Compatibility UC1, UC2, UC3 ────────────────────────────

    @Test
    void testBackwardCompatibility_FeetEquality() {
        // UC1 still works
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testBackwardCompatibility_InchEquality() {
        // UC2 still works
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCHES);
        assertEquals(inch1, inch2);
    }

    @Test
    void testBackwardCompatibility_FeetToInchCrossUnit() {
        // UC3 still works
        QuantityLength oneFoot    = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInch = new QuantityLength(12.0, LengthUnit.INCHES);
        assertEquals(oneFoot, twelveInch);
    }
}
