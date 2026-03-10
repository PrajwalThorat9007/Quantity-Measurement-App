import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 1e-4;

    // ───────────────── Length Equality ─────────────────

    @Test
    void testLengthFeetEqualsInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        assertEquals(feet, inches);
    }

    @Test
    void testLengthYardsEqualsFeet() {

        Quantity<LengthUnit> yard =
                new Quantity<>(1.0, LengthUnit.YARDS);

        Quantity<LengthUnit> feet =
                new Quantity<>(3.0, LengthUnit.FEET);

        assertEquals(yard, feet);
    }

    // ───────────────── Length Conversion ─────────────────

    @Test
    void testConvertFeetToInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> result =
                feet.convertTo(LengthUnit.INCHES);

        assertEquals(new Quantity<>(12.0, LengthUnit.INCHES), result);
    }

    // ───────────────── Length Addition ─────────────────

    @Test
    void testAddFeetAndInches() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<LengthUnit> inches =
                new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = feet.add(inches);

        assertEquals(new Quantity<>(2.0, LengthUnit.FEET), result);
    }

    // ───────────────── Weight Equality ─────────────────

    @Test
    void testKilogramEqualsGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        assertEquals(kg, gram);
    }

    @Test
    void testVolumeEquality() {

        Quantity<VolumeUnit> litre =
                new Quantity<>(1.0, VolumeUnit.LITRE);

        Quantity<VolumeUnit> ml =
                new Quantity<>(1000.0, VolumeUnit.MILLILITRE);

        assertEquals(litre, ml);
    }

    @Test
    void testGramEqualsKilogram() {

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertEquals(gram, kg);
    }

    // ───────────────── Weight Conversion ─────────────────

    @Test
    void testConvertKgToGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> result =
                kg.convertTo(WeightUnit.GRAM);

        assertEquals(new Quantity<>(1000.0, WeightUnit.GRAM), result);
    }

    // ───────────────── Weight Addition ─────────────────

    @Test
    void testAddKgAndGram() {

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        Quantity<WeightUnit> gram =
                new Quantity<>(1000.0, WeightUnit.GRAM);

        Quantity<WeightUnit> result = kg.add(gram);

        assertEquals(new Quantity<>(2.0, WeightUnit.KILOGRAM), result);
    }

    // ───────────────── Cross Category Safety ─────────────────

    @Test
    void testLengthNotEqualWeight() {

        Quantity<LengthUnit> feet =
                new Quantity<>(1.0, LengthUnit.FEET);

        Quantity<WeightUnit> kg =
                new Quantity<>(1.0, WeightUnit.KILOGRAM);

        assertNotEquals(feet, kg);
    }

    // ───────────────── Constructor Validation ─────────────────

    @Test
    void testNullUnitThrowsException() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(1.0, null));
    }

    @Test
    void testNaNThrowsException() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));
    }

    @Test
    void testInfiniteThrowsException() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }

    // ───────────────── Subtraction Tests ─────────────────

    @Test
    void testSubtraction_SameUnit_FeetMinusFeet() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    void testSubtraction_SameUnit_LitreMinusLitre() {

        Quantity<VolumeUnit> a = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(3.0, VolumeUnit.LITRE);

        assertEquals(new Quantity<>(7.0, VolumeUnit.LITRE), a.subtract(b));
    }

    @Test
    void testSubtraction_CrossUnit_FeetMinusInches() {

        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(9.5, LengthUnit.FEET), feet.subtract(inches));
    }

    @Test
    void testSubtraction_CrossUnit_InchesMinusFeet() {

        Quantity<LengthUnit> inches = new Quantity<>(120.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(5.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(60.0, LengthUnit.INCHES), inches.subtract(feet));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Feet() {

        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(9.5, LengthUnit.FEET),
                feet.subtract(inches, LengthUnit.FEET));
    }

    @Test
    void testSubtraction_ExplicitTargetUnit_Inches() {

        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);

        assertEquals(
                new Quantity<>(114.0, LengthUnit.INCHES),
                feet.subtract(inches, LengthUnit.INCHES));
    }

    @Test
    void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(-5.0, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    void testSubtraction_ResultingInZero() {

        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(120.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(0.0, LengthUnit.FEET), feet.subtract(inches));
    }

    @Test
    void testSubtraction_WithZeroOperand() {

        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.INCHES);

        assertEquals(new Quantity<>(5.0, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    void testSubtraction_WithNegativeValues() {

        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(-2.0, LengthUnit.FEET);

        assertEquals(new Quantity<>(7.0, LengthUnit.FEET), a.subtract(b));
    }

    @Test
    void testSubtraction_NonCommutative() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        assertNotEquals(a.subtract(b), b.subtract(a));
    }

    @Test
    void testSubtraction_NullOperand() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.subtract(null));
    }
    // ───────────────── Division Tests ─────────────────

    @Test
    void testDivision_SameUnit_FeetDividedByFeet() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(5.0, a.divide(b));
    }

    @Test
    void testDivision_SameUnit_LitreDividedByLitre() {

        Quantity<VolumeUnit> a = new Quantity<>(10.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> b = new Quantity<>(5.0, VolumeUnit.LITRE);

        assertEquals(2.0, a.divide(b));
    }

    @Test
    void testDivision_CrossUnit_FeetDividedByInches() {

        Quantity<LengthUnit> inches = new Quantity<>(24.0, LengthUnit.INCHES);
        Quantity<LengthUnit> feet = new Quantity<>(2.0, LengthUnit.FEET);

        assertEquals(1.0, inches.divide(feet));
    }

    @Test
    void testDivision_CrossUnit_KilogramDividedByGram() {

        Quantity<WeightUnit> kg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> g = new Quantity<>(2000.0, WeightUnit.GRAM);

        assertEquals(1.0, kg.divide(g));
    }

    @Test
    void testDivision_RatioGreaterThanOne() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        assertTrue(a.divide(b) > 1.0);
    }

    @Test
    void testDivision_RatioLessThanOne() {

        Quantity<LengthUnit> a = new Quantity<>(5.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);

        assertTrue(a.divide(b) < 1.0);
    }

    @Test
    void testDivision_RatioEqualToOne() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(10.0, LengthUnit.FEET);

        assertEquals(1.0, a.divide(b));
    }

    @Test
    void testDivision_ByZero() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.0, LengthUnit.FEET);

        assertThrows(ArithmeticException.class, () -> a.divide(b));
    }

    @Test
    void testDivision_NullOperand() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> a.divide(null));
    }
    @Test
    void testSubtractionAndDivision_Integration() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> c = new Quantity<>(4.0, LengthUnit.FEET);

        double result = a.subtract(b).divide(c);

        assertEquals(2.0, result);
    }
    @Test
    void testSubtraction_Immutability() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        a.subtract(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }

    @Test
    void testDivision_Immutability() {

        Quantity<LengthUnit> a = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(5.0, LengthUnit.FEET);

        a.divide(b);

        assertEquals(new Quantity<>(10.0, LengthUnit.FEET), a);
    }

}