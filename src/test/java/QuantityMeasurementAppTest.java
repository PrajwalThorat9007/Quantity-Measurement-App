import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    // ── Feet Test Cases ──────────────────────────────────────────────────

    @Test
    void testFeetEquality_SameValue() {
        // same feet value should be equal
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        assertEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_DifferentValue() {
        // different feet value should not be equal
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(2.0);
        assertNotEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_NullComparison() {
        // feet compared to null should not be equal
        Feet feet1 = new Feet(1.0);
        assertNotEquals(feet1, null);
    }

    @Test
    void testFeetEquality_NonNumericInput() {
        // feet compared to a string should not be equal
        Feet feet1 = new Feet(1.0);
        assertNotEquals(feet1, "1.0");
    }

    @Test
    void testFeetEquality_SameReference() {
        // same reference should always be equal
        Feet feet1 = new Feet(1.0);
        assertEquals(feet1, feet1);
    }

    @Test
    void testFeetEquality_ZeroValue() {
        // zero feet should be equal
        Feet feet1 = new Feet(0.0);
        Feet feet2 = new Feet(0.0);
        assertEquals(feet1, feet2);
    }

    @Test
    void testFeetEquality_NegativeValue() {
        // same negative feet value should be equal
        Feet feet1 = new Feet(-1.0);
        Feet feet2 = new Feet(-1.0);
        assertEquals(feet1, feet2);
    }

    // ── Inches Test Cases ────────────────────────────────────────────────

    @Test
    void testInchesEquality_SameValue() {
        // same inches value should be equal
        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(1.0);
        assertEquals(inch1, inch2);
    }

    @Test
    void testInchesEquality_DifferentValue() {
        // different inches value should not be equal
        Inches inch1 = new Inches(1.0);
        Inches inch2 = new Inches(2.0);
        assertNotEquals(inch1, inch2);
    }

    @Test
    void testInchesEquality_NullComparison() {
        // inches compared to null should not be equal
        Inches inch1 = new Inches(1.0);
        assertNotEquals(inch1, null);
    }

    @Test
    void testInchesEquality_NonNumericInput() {
        // inches compared to string should not be equal
        Inches inch1 = new Inches(1.0);
        assertNotEquals(inch1, "1.0");
    }

    @Test
    void testInchesEquality_SameReference() {
        // same reference should always be equal
        Inches inch1 = new Inches(1.0);
        assertEquals(inch1, inch1);
    }

    @Test
    void testInchesEquality_ZeroValue() {
        // zero inches should be equal
        Inches inch1 = new Inches(0.0);
        Inches inch2 = new Inches(0.0);
        assertEquals(inch1, inch2);
    }

    @Test
    void testInchesEquality_NegativeValue() {
        // same negative inches value should be equal
        Inches inch1 = new Inches(-1.0);
        Inches inch2 = new Inches(-1.0);
        assertEquals(inch1, inch2);
    }

    // ── Cross Type Test Cases ────────────────────────────────────────────

    @Test
    void testFeetAndInches_AreNotEqual() {
        // feet and inches with same value should NOT be equal
        // they are different types
        Feet feet1   = new Feet(1.0);
        Inches inch1 = new Inches(1.0);
        assertNotEquals(feet1, inch1);
    }

    // ── App Method Test Cases ────────────────────────────────────────────

    @Test
    void testCompareFeet_SameValue() {
        // static method should return true for same feet value
        assertTrue(QuantityMeasurementApp.compareFeet(1.0, 1.0));
    }

    @Test
    void testCompareFeet_DifferentValue() {
        // static method should return false for different feet value
        assertFalse(QuantityMeasurementApp.compareFeet(1.0, 2.0));
    }

    @Test
    void testCompareInches_SameValue() {
        // static method should return true for same inches value
        assertTrue(QuantityMeasurementApp.compareInches(1.0, 1.0));
    }

    @Test
    void testCompareInches_DifferentValue() {
        // static method should return false for different inches value
        assertFalse(QuantityMeasurementApp.compareInches(1.0, 2.0));
    }
}