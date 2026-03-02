import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-6;

    // ── Same Unit Addition ───────────────────────────────────────────────

    @Test
    void testAddition_SameUnit_FeetPlusFeet() {
        // 1 foot + 2 feet = 3 feet
        QuantityLength a      = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(2.0, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SameUnit_InchPlusInch() {
        // 6 inches + 6 inches = 12 inches
        QuantityLength a      = new QuantityLength(6.0, LengthUnit.INCHES);
        QuantityLength b      = new QuantityLength(6.0, LengthUnit.INCHES);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_SameUnit_YardPlusYard() {
        // 1 yard + 1 yard = 2 yards
        QuantityLength a      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    // ── Cross Unit Addition ──────────────────────────────────────────────

    @Test
    void testAddition_CrossUnit_FeetPlusInches() {
        // 1 foot + 12 inches = 2 feet
        QuantityLength a      = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusFeet() {
        // 12 inches + 1 foot = 24 inches (result in first operand unit)
        QuantityLength a      = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength b      = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_YardPlusFeet() {
        // 1 yard + 3 feet = 2 yards
        QuantityLength a      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength b      = new QuantityLength(3.0, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(2.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_CrossUnit_InchPlusYard() {
        // 36 inches + 1 yard = 72 inches
        QuantityLength a      = new QuantityLength(36.0, LengthUnit.INCHES);
        QuantityLength b      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(72.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_CrossUnit_CentimeterPlusInch() {
        // 2.54 cm + 1 inch ≈ 5.08 cm
        QuantityLength a      = new QuantityLength(2.54, LengthUnit.CENTIMETERS);
        QuantityLength b      = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(5.08, LengthUnit.CENTIMETERS), result);
    }

    // ── Mathematical Properties ──────────────────────────────────────────

    @Test
    void testAddition_Commutativity() {
        // add(A, B) in base unit == add(B, A) in base unit
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        // convert both results to base unit for comparison
        QuantityLength resultAB = a.add(b);
        QuantityLength resultBA = b.add(a);

        // both should represent same physical length
        assertEquals(resultAB.convertTo(LengthUnit.FEET),
                resultBA.convertTo(LengthUnit.FEET));
    }

    @Test
    void testAddition_WithZero() {
        // 5 feet + 0 inches = 5 feet
        QuantityLength a      = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(0.0, LengthUnit.INCHES);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(5.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_NegativeValues() {
        // 5 feet + (-2 feet) = 3 feet
        QuantityLength a      = new QuantityLength(5.0, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(-2.0, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(3.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_LargeValues() {
        // 1e6 feet + 1e6 feet = 2e6 feet
        QuantityLength a      = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(1e6, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(2e6, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_SmallValues() {
        // 0.001 feet + 0.002 feet ≈ 0.003 feet
        QuantityLength a      = new QuantityLength(0.001, LengthUnit.FEET);
        QuantityLength b      = new QuantityLength(0.002, LengthUnit.FEET);
        QuantityLength result = a.add(b);
        assertEquals(new QuantityLength(0.003, LengthUnit.FEET), result);
    }

    // ── Immutability ─────────────────────────────────────────────────────

    @Test
    void testAddition_OriginalUnchanged() {
        // add should not modify original objects
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(2.0, LengthUnit.FEET);
        a.add(b);

        // a and b should still have original values
        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), a);
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), b);
    }

    // ── Null Handling ────────────────────────────────────────────────────

    @Test
    void testAddition_NullSecondOperand_Throws() {
        // adding null should throw IllegalArgumentException
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () -> a.add(null));
    }

    @Test
    void testAddition_StaticMethod_NullFirst_Throws() {
        // static add with null first operand should throw
        QuantityLength b = new QuantityLength(1.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(null, b, LengthUnit.FEET));
    }

    @Test
    void testAddition_StaticMethod_NullTarget_Throws() {
        // static add with null target unit should throw
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(2.0, LengthUnit.FEET);
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(a, b, null));
    }

    // ── Backward Compatibility ───────────────────────────────────────────

    @Test
    void testBackwardCompatibility_Equality() {
        // UC3 equality still works
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        assertEquals(feet1, feet2);
    }

    @Test
    void testBackwardCompatibility_Conversion() {
        // UC5 conversion still works
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testBackwardCompatibility_CrossUnitEquality() {
        // UC4 cross unit equality still works
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        assertEquals(oneYard, threeFeet);
    }
}