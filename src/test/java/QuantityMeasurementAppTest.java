import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ── WeightUnit Enum ──────────────────────────────────────────────────

    @Test
    void testWeightUnitEnum_KilogramFactor() {
        // kilogram is base unit, factor = 1.0
        assertEquals(1.0, WeightUnit.KILOGRAM.getConversionFactor(), EPSILON);
    }

    @Test
    void testWeightUnitEnum_GramFactor() {
        // 1 gram = 0.001 kg
        assertEquals(0.001, WeightUnit.GRAM.getConversionFactor(), EPSILON);
    }

    @Test
    void testWeightUnitEnum_PoundFactor() {
        // 1 pound = 0.453592 kg
        assertEquals(0.453592, WeightUnit.POUND.getConversionFactor(), EPSILON);
    }

    // ── WeightUnit convertToBaseUnit ─────────────────────────────────────

    @Test
    void testConvertToBaseUnit_KilogramToKilogram() {
        // 5 kg → base = 5 kg (no change)
        assertEquals(5.0, WeightUnit.KILOGRAM.convertToBaseUnit(5.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_GramToKilogram() {
        // 1000 grams → base = 1 kg
        assertEquals(1.0, WeightUnit.GRAM.convertToBaseUnit(1000.0), EPSILON);
    }

    @Test
    void testConvertToBaseUnit_PoundToKilogram() {
        // 1 pound → base = 0.453592 kg
        assertEquals(0.453592, WeightUnit.POUND.convertToBaseUnit(1.0), EPSILON);
    }

    // ── WeightUnit convertFromBaseUnit ───────────────────────────────────

    @Test
    void testConvertFromBaseUnit_KilogramToKilogram() {
        // 2 kg base → 2 kg (no change)
        assertEquals(2.0, WeightUnit.KILOGRAM.convertFromBaseUnit(2.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_KilogramToGram() {
        // 1 kg base → 1000 grams
        assertEquals(1000.0, WeightUnit.GRAM.convertFromBaseUnit(1.0), EPSILON);
    }

    @Test
    void testConvertFromBaseUnit_KilogramToPound() {
        // 1 kg base → 2.20462 pounds
        assertEquals(2.20462, WeightUnit.POUND.convertFromBaseUnit(1.0), EPSILON);
    }

    // ── Same Unit Equality ───────────────────────────────────────────────

    @Test
    void testEquality_KilogramToKilogram_SameValue() {
        // same kilogram value should be equal
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_GramToGram_SameValue() {
        // same gram value should be equal
        assertEquals(
                new QuantityWeight(500.0, WeightUnit.GRAM),
                new QuantityWeight(500.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_PoundToPound_SameValue() {
        // same pound value should be equal
        assertEquals(
                new QuantityWeight(2.0, WeightUnit.POUND),
                new QuantityWeight(2.0, WeightUnit.POUND));
    }

    @Test
    void testEquality_KilogramToKilogram_DifferentValue() {
        // different kilogram values should not be equal
        assertNotEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.0, WeightUnit.KILOGRAM));
    }

    // ── Cross Unit Equality ──────────────────────────────────────────────

    @Test
    void testEquality_KilogramToGram_EquivalentValue() {
        // 1 kg == 1000 grams
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_GramToKilogram_EquivalentValue() {
        // 1000 grams == 1 kg (symmetry)
        assertEquals(
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_KilogramToPound_EquivalentValue() {
        // 1 kg == 2.20462 pounds
        assertEquals(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(2.20462, WeightUnit.POUND));
    }

    @Test
    void testEquality_GramToPound_EquivalentValue() {
        // 453.592 grams == 1 pound
        assertEquals(
                new QuantityWeight(453.592, WeightUnit.GRAM),
                new QuantityWeight(1.0, WeightUnit.POUND));
    }

    @Test
    void testEquality_SmallWeight() {
        // 0.001 kg == 1 gram
        assertEquals(
                new QuantityWeight(0.001, WeightUnit.KILOGRAM),
                new QuantityWeight(1.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_LargeWeight() {
        // 1000000 grams == 1000 kg
        assertEquals(
                new QuantityWeight(1000000.0, WeightUnit.GRAM),
                new QuantityWeight(1000.0, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_ZeroWeight() {
        // 0 kg == 0 grams
        assertEquals(
                new QuantityWeight(0.0, WeightUnit.KILOGRAM),
                new QuantityWeight(0.0, WeightUnit.GRAM));
    }

    @Test
    void testEquality_NegativeWeight() {
        // -1 kg == -1000 grams
        assertEquals(
                new QuantityWeight(-1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(-1000.0, WeightUnit.GRAM));
    }

    // ── Equality Contract ────────────────────────────────────────────────

    @Test
    void testEquality_SameReference() {
        // reflexive - object must equal itself
        QuantityWeight w = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(w, w);
    }

    @Test
    void testEquality_NullComparison() {
        // weight compared to null should return false
        assertNotEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), null);
    }

    @Test
    void testEquality_TransitiveProperty() {
        // if a==b and b==c then a==c
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        QuantityWeight c = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        assertEquals(a, b);
        assertEquals(b, c);
        assertEquals(a, c);
    }

    // ── Category Type Safety ─────────────────────────────────────────────

    @Test
    void testEquality_WeightVsLength_Incompatible() {
        // weight and length with same numeric value should NOT be equal
        QuantityWeight kg    = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityLength feet  = new QuantityLength(1.0, LengthUnit.FEET);
        assertNotEquals(kg, feet);
    }

    // ── Null and Invalid Input ───────────────────────────────────────────

    @Test
    void testEquality_NullUnit_Throws() {
        // null unit should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, null));
    }

    @Test
    void testEquality_NaNValue_Throws() {
        // NaN should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(Double.NaN, WeightUnit.KILOGRAM));
    }

    @Test
    void testEquality_InfiniteValue_Throws() {
        // infinite value should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(Double.POSITIVE_INFINITY, WeightUnit.KILOGRAM));
    }

    // ── Conversion ───────────────────────────────────────────────────────

    @Test
    void testConversion_KilogramToGram() {
        // 1 kg → 1000 grams
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_GramToKilogram() {
        // 1000 grams → 1 kg
        QuantityWeight result = new QuantityWeight(1000.0, WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_KilogramToPound() {
        // 1 kg → 2.20462 pounds
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.POUND);
        assertEquals(new QuantityWeight(2.20462, WeightUnit.POUND), result);
    }

    @Test
    void testConversion_PoundToKilogram() {
        // 2.20462 pounds → 1 kg
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_SameUnit() {
        // converting to same unit returns same value
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(new QuantityWeight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testConversion_ZeroValue() {
        // zero kg → zero grams
        QuantityWeight result = new QuantityWeight(0.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(new QuantityWeight(0.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_NegativeValue() {
        // -1 kg → -1000 grams
        QuantityWeight result = new QuantityWeight(-1.0, WeightUnit.KILOGRAM)
                .convertTo(WeightUnit.GRAM);
        assertEquals(new QuantityWeight(-1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testConversion_RoundTrip() {
        // kg → gram → kg should preserve value
        QuantityWeight original = new QuantityWeight(1.5, WeightUnit.KILOGRAM);
        QuantityWeight result   = original.convertTo(WeightUnit.GRAM)
                .convertTo(WeightUnit.KILOGRAM);
        assertEquals(original, result);
    }

    // ── Addition Same Unit ───────────────────────────────────────────────

    @Test
    void testAddition_SameUnit_KilogramPlusKilogram() {
        // 1 kg + 2 kg = 3 kg
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(2.0, WeightUnit.KILOGRAM));
        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_SameUnit_GramPlusGram() {
        // 500 g + 500 g = 1000 g
        QuantityWeight result = new QuantityWeight(500.0, WeightUnit.GRAM)
                .add(new QuantityWeight(500.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), result);
    }

    // ── Addition Cross Unit ──────────────────────────────────────────────

    @Test
    void testAddition_CrossUnit_KilogramPlusGram() {
        // 1 kg + 1000 g = 2 kg
        QuantityWeight result = new QuantityWeight(1.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1000.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(2.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_CrossUnit_GramPlusKilogram() {
        // 500 g + 0.5 kg = 1000 g
        QuantityWeight result = new QuantityWeight(500.0, WeightUnit.GRAM)
                .add(new QuantityWeight(0.5, WeightUnit.KILOGRAM));
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_CrossUnit_PoundPlusKilogram() {
        // 2.20462 lbs + 1 kg = 4.40924 lbs
        QuantityWeight result = new QuantityWeight(2.20462, WeightUnit.POUND)
                .add(new QuantityWeight(1.0, WeightUnit.KILOGRAM));
        assertEquals(new QuantityWeight(4.40924, WeightUnit.POUND), result);
    }

    // ── Addition Explicit Target Unit ────────────────────────────────────

    @Test
    void testAddition_ExplicitTargetUnit_Gram() {
        // 1 kg + 1000 g → result in GRAM = 2000 g
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(1.0, WeightUnit.KILOGRAM),
                new QuantityWeight(1000.0, WeightUnit.GRAM),
                WeightUnit.GRAM);
        assertEquals(new QuantityWeight(2000.0, WeightUnit.GRAM), result);
    }

    @Test
    void testAddition_ExplicitTargetUnit_Kilogram() {
        // 2 kg + 4 lb → result in KILOGRAM
        QuantityWeight result = QuantityWeight.add(
                new QuantityWeight(2.0, WeightUnit.KILOGRAM),
                new QuantityWeight(4.0, WeightUnit.POUND),
                WeightUnit.KILOGRAM);
        assertEquals(new QuantityWeight(3.81, WeightUnit.KILOGRAM), result);
    }

    // ── Mathematical Properties ──────────────────────────────────────────

    @Test
    void testAddition_WithZero() {
        // 5 kg + 0 g = 5 kg
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(0.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(5.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_NegativeValues() {
        // 5 kg + (-2000 g) = 3 kg
        QuantityWeight result = new QuantityWeight(5.0, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(-2000.0, WeightUnit.GRAM));
        assertEquals(new QuantityWeight(3.0, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_LargeValues() {
        // 1e6 kg + 1e6 kg = 2e6 kg
        QuantityWeight result = new QuantityWeight(1e6, WeightUnit.KILOGRAM)
                .add(new QuantityWeight(1e6, WeightUnit.KILOGRAM));
        assertEquals(new QuantityWeight(2e6, WeightUnit.KILOGRAM), result);
    }

    @Test
    void testAddition_Commutativity() {
        // add(A, B) and add(B, A) should represent same physical weight
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);

        QuantityWeight resultAB = a.add(b).convertTo(WeightUnit.KILOGRAM);
        QuantityWeight resultBA = b.add(a).convertTo(WeightUnit.KILOGRAM);
        assertEquals(resultAB, resultBA);
    }

    @Test
    void testAddition_OriginalUnchanged() {
        // original objects must not be modified after add
        QuantityWeight a = new QuantityWeight(1.0, WeightUnit.KILOGRAM);
        QuantityWeight b = new QuantityWeight(1000.0, WeightUnit.GRAM);
        a.add(b);
        assertEquals(new QuantityWeight(1.0, WeightUnit.KILOGRAM), a);
        assertEquals(new QuantityWeight(1000.0, WeightUnit.GRAM), b);
    }

    // ── Null Handling ────────────────────────────────────────────────────

    @Test
    void testAddition_NullOperand_Throws() {
        // null operand should throw IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () ->
                new QuantityWeight(1.0, WeightUnit.KILOGRAM).add(null));
    }

    // ── Backward Compatibility ───────────────────────────────────────────

    @Test
    void testBackwardCompatibility_LengthEquality() {
        // UC8 length equality still works
        assertEquals(
                new QuantityLength(1.0, LengthUnit.FEET),
                new QuantityLength(12.0, LengthUnit.INCHES));
    }

    @Test
    void testBackwardCompatibility_LengthConversion() {
        // UC5 length conversion still works
        assertEquals(12.0,
                QuantityLength.convert(1.0, LengthUnit.FEET, LengthUnit.INCHES), EPSILON);
    }

    @Test
    void testBackwardCompatibility_LengthAddition() {
        // UC6 length addition still works
        assertEquals(
                new QuantityLength(2.0, LengthUnit.FEET),
                new QuantityLength(1.0, LengthUnit.FEET)
                        .add(new QuantityLength(12.0, LengthUnit.INCHES)));
    }
}