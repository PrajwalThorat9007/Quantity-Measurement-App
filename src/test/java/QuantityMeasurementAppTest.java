import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ── Same Unit Equality ───────────────────────────────────────────────

    @Test
    void testEquality_FeetToFeet_SameValue() {
        // same feet value should be equal
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testEquality_InchToInch_SameValue() {
        // same inch value should be equal
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCHES);
        assertEquals(inch1, inch2);
    }

    @Test
    void testEquality_FeetToFeet_DifferentValue() {
        // different feet values should not be equal
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(2.0, LengthUnit.FEET);
        assertNotEquals(feet1, feet2);
    }

    @Test
    void testEquality_InchToInch_DifferentValue() {
        // different inch values should not be equal
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(2.0, LengthUnit.INCHES);
        assertNotEquals(inch1, inch2);
    }

    // ── Cross Unit Equality ──────────────────────────────────────────────

    @Test
    void testEquality_FeetToInch_EquivalentValue() {
        // 1 feet == 12 inches
        QuantityLength oneFoot    = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInch = new QuantityLength(12.0, LengthUnit.INCHES);
        assertEquals(oneFoot, twelveInch);
    }

    @Test
    void testEquality_InchToFeet_EquivalentValue() {
        // 12 inches == 1 feet (symmetry check)
        QuantityLength twelveInch = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength oneFoot    = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(twelveInch, oneFoot);
    }

    @Test
    void testEquality_FeetToInch_NotEquivalent() {
        // 1 feet != 1 inch
        QuantityLength oneFoot = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength oneInch = new QuantityLength(1.0, LengthUnit.INCHES);
        assertNotEquals(oneFoot, oneInch);
    }

    // ── Equality Contract ────────────────────────────────────────────────

    @Test
    void testEquality_SameReference() {
        // reflexive - object must equal itself
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet1);
    }

    @Test
    void testEquality_Symmetric() {
        // if a==b then b==a
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
        assertEquals(feet2, feet1);
    }

    @Test
    void testEquality_Transitive() {
        // if a==b and b==c then a==c
        QuantityLength feet1     = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2     = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet3     = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
        assertEquals(feet2, feet3);
        assertEquals(feet1, feet3);
    }

    // ── Null and Type Safety ─────────────────────────────────────────────

    @Test
    void testEquality_NullComparison() {
        // object compared to null should not be equal
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(feet1, null);
    }

    @Test
    void testEquality_NonQuantityType() {
        // object compared to string should not be equal
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(feet1, "1.0");
    }

    @Test
    void testEquality_NullUnit() {
        // null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> {
            new QuantityLength(1.0, null);
        });
    }

    // ── Edge Cases ───────────────────────────────────────────────────────

    @Test
    void testEquality_ZeroFeet() {
        // zero feet should equal zero feet
        QuantityLength feet1 = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(0.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testEquality_ZeroInches() {
        // zero inches should equal zero inches
        QuantityLength inch1 = new QuantityLength(0.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(0.0, LengthUnit.INCHES);
        assertEquals(inch1, inch2);
    }

    @Test
    void testEquality_ZeroFeetAndZeroInches() {
        // 0 feet should equal 0 inches
        QuantityLength feet1 = new QuantityLength(0.0, LengthUnit.FEET);
        QuantityLength inch1 = new QuantityLength(0.0, LengthUnit.INCHES);
        assertEquals(feet1, inch1);
    }

    // ── Backward Compatibility UC1 and UC2 ───────────────────────────────

    @Test
    void testBackwardCompatibility_UC1_FeetEquality() {
        // UC1 feet equality still works
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testBackwardCompatibility_UC2_InchEquality() {
        // UC2 inch equality still works
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCHES);
        assertEquals(inch1, inch2);
    }
}