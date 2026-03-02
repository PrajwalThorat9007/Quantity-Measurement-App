import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-2;

    // ── Explicit Target Unit ─────────────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_Feet() {
        // 1 foot + 12 inches → result in FEET = 2.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.FEET);
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Inches() {
        // 1 foot + 12 inches → result in INCHES = 24.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.INCHES);
        assertEquals(new QuantityLength(24.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Yards() {
        // 1 foot + 12 inches → result in YARDS ≈ 0.67
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);
        assertEquals(new QuantityLength(0.67, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Centimeters() {
        // 1 inch + 1 inch → result in CENTIMETERS ≈ 5.08
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.INCHES),
                LengthUnit.CENTIMETERS);
        assertEquals(new QuantityLength(5.08, LengthUnit.CENTIMETERS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsFirstOperand() {
        // 2 yards + 3 feet → result in YARDS = 3.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.YARDS);
        assertEquals(new QuantityLength(3.0, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SameAsSecondOperand() {
        // 2 yards + 3 feet → result in FEET = 9.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(2.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET),
                LengthUnit.FEET);
        assertEquals(new QuantityLength(9.0, LengthUnit.FEET), result);
    }

    // ── Commutativity ────────────────────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_Commutativity() {
        // add(A, B, YARDS) == add(B, A, YARDS)
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength resultAB = QuantityLength.add(a, b, LengthUnit.YARDS);
        QuantityLength resultBA = QuantityLength.add(b, a, LengthUnit.YARDS);
        assertEquals(resultAB, resultBA);
    }

    // ── Edge Cases ───────────────────────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_WithZero() {
        // 5 feet + 0 inches → result in YARDS ≈ 1.67
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(0.0, LengthUnit.INCHES),
                LengthUnit.YARDS);
        assertEquals(new QuantityLength(1.67, LengthUnit.YARDS), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_NegativeValues() {
        // 5 feet + (-2 feet) → result in INCHES = 36.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(5.0, LengthUnit.FEET),
                new QuantityLength(-2.0, LengthUnit.FEET),
                LengthUnit.INCHES);
        assertEquals(new QuantityLength(36.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_LargeToSmallScale() {
        // 1000 feet + 500 feet → result in INCHES = 18000.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(1000.0, LengthUnit.FEET),
                new QuantityLength(500.0, LengthUnit.FEET),
                LengthUnit.INCHES);
        assertEquals(new QuantityLength(18000.0, LengthUnit.INCHES), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_SmallToLargeScale() {
        // 12 inches + 12 inches → result in YARDS ≈ 0.67
        QuantityLength result = QuantityLength.add(
                new QuantityLength(12.0, LengthUnit.INCHES),
                new QuantityLength(12.0, LengthUnit.INCHES),
                LengthUnit.YARDS);
        assertEquals(new QuantityLength(0.67, LengthUnit.YARDS), result);
    }

    // ── Mathematical Correctness ─────────────────────────────────────────

    @Test
    void testAddition_SameAdditionDifferentTargetUnits() {
        // 1 foot + 12 inches expressed in different units must be equivalent
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);

        QuantityLength inFeet   = QuantityLength.add(a, b, LengthUnit.FEET);
        QuantityLength inInches = QuantityLength.add(a, b, LengthUnit.INCHES);
        QuantityLength inYards  = QuantityLength.add(a, b, LengthUnit.YARDS);

        // all three represent the same physical length
        assertEquals(inFeet, inInches);
        assertEquals(inFeet, inYards);
    }

    @Test
    void testAddition_ExplicitTargetUnit_AllUnitCombinations() {
        // 36 inches + 1 yard → result in FEET = 6.0
        QuantityLength result = QuantityLength.add(
                new QuantityLength(36.0, LengthUnit.INCHES),
                new QuantityLength(1.0, LengthUnit.YARDS),
                LengthUnit.FEET);
        assertEquals(new QuantityLength(6.0, LengthUnit.FEET), result);
    }

    // ── Null Handling ────────────────────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_NullTargetUnit() {
        // null target unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        null));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullFirstOperand() {
        // null first operand should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(null,
                        new QuantityLength(12.0, LengthUnit.INCHES),
                        LengthUnit.FEET));
    }

    @Test
    void testAddition_ExplicitTargetUnit_NullSecondOperand() {
        // null second operand should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                QuantityLength.add(
                        new QuantityLength(1.0, LengthUnit.FEET),
                        null,
                        LengthUnit.FEET));
    }

    // ── Immutability ─────────────────────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_OriginalUnchanged() {
        // original objects must not be modified after add
        QuantityLength a = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength b = new QuantityLength(12.0, LengthUnit.INCHES);
        QuantityLength.add(a, b, LengthUnit.YARDS);

        assertEquals(new QuantityLength(1.0, LengthUnit.FEET), a);
        assertEquals(new QuantityLength(12.0, LengthUnit.INCHES), b);
    }

    // ── Backward Compatibility ───────────────────────────────────────────

    @Test
    void testBackwardCompatibility_UC6_AddDefaultUnit() {
        // UC6 add() still returns result in first operand unit
        QuantityLength result = new QuantityLength(1.0, LengthUnit.FEET)
                .add(new QuantityLength(12.0, LengthUnit.INCHES));
        assertEquals(new QuantityLength(2.0, LengthUnit.FEET), result);
    }

    @Test
    void testBackwardCompatibility_UC5_Conversion() {
        // UC5 conversion still works
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testBackwardCompatibility_UC4_CrossUnitEquality() {
        // UC4 equality still works
        assertEquals(
                new QuantityLength(1.0, LengthUnit.YARDS),
                new QuantityLength(3.0, LengthUnit.FEET));
    }
}