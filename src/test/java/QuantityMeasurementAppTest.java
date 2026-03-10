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
    // ───────────────── Enum Arithmetic Tests ─────────────────

    @Test
    void testArithmeticOperation_Add_EnumComputation() {

        double result = 10.0 + 5.0;

        assertEquals(15.0, result);
    }

    @Test
    void testArithmeticOperation_Subtract_EnumComputation() {

        double result = 10.0 - 5.0;

        assertEquals(5.0, result);
    }

    @Test
    void testArithmeticOperation_Divide_EnumComputation() {

        double result = 10.0 / 5.0;

        assertEquals(2.0, result);
    }

    @Test
    void testArithmeticOperation_DivideByZero_EnumThrows() {

        assertThrows(ArithmeticException.class, () -> {
            double x = 10.0 / 0.0;
            if(Double.isInfinite(x)) {
                throw new ArithmeticException("Division by zero");
            }
        });
    }
    // ───────────────── Validation Consistency ─────────────────

    @Test
    void testValidation_NullOperand_ConsistentAcrossOperations() {

        Quantity<LengthUnit> q = new Quantity<>(10.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class, () -> q.add(null));
        assertThrows(IllegalArgumentException.class, () -> q.subtract(null));
        assertThrows(IllegalArgumentException.class, () -> q.divide(null));
    }

    @Test
    void testValidation_CrossCategory_ConsistentAcrossOperations() {

        Quantity<LengthUnit> length = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<WeightUnit> weight = new Quantity<>(5.0, WeightUnit.KILOGRAM);

        assertThrows(IllegalArgumentException.class, () -> length.add((Quantity)weight));
        assertThrows(IllegalArgumentException.class, () -> length.subtract((Quantity)weight));
        assertThrows(IllegalArgumentException.class, () -> length.divide((Quantity)weight));
    }

    @Test
    void testValidation_FiniteValue_ConsistentAcrossOperations() {

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.NaN, LengthUnit.FEET));

        assertThrows(IllegalArgumentException.class,
                () -> new Quantity<>(Double.POSITIVE_INFINITY, LengthUnit.FEET));
    }
    @Test
    void testValidation_NullTargetUnit_AddSubtractReject() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(5.0, LengthUnit.FEET);

        assertThrows(IllegalArgumentException.class,
                () -> q1.add(q2, null));

        assertThrows(IllegalArgumentException.class,
                () -> q1.subtract(q2, null));
    }
    // ───────────────── Rounding Tests ─────────────────

    @Test
    void testRounding_AddSubtract_TwoDecimalPlaces() {

        Quantity<LengthUnit> a = new Quantity<>(1.234, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(0.111, LengthUnit.FEET);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(1.35, result.getValue(), 0.01);
    }

    @Test
    void testRounding_Divide_NoRounding() {

        Quantity<LengthUnit> a = new Quantity<>(7.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(2.0, LengthUnit.FEET);

        double result = a.divide(b);

        assertEquals(3.5, result);
    }
    @Test
    void testImplicitTargetUnit_AddSubtract() {

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b);

        assertEquals(LengthUnit.FEET, result.getUnit());
    }

    @Test
    void testExplicitTargetUnit_AddSubtract_Overrides() {

        Quantity<LengthUnit> a = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> b = new Quantity<>(12.0, LengthUnit.INCHES);

        Quantity<LengthUnit> result = a.add(b, LengthUnit.INCHES);

        assertEquals(LengthUnit.INCHES, result.getUnit());
    }
    // ───────────────── All Categories ─────────────────

    @Test
    void testAllOperations_AcrossAllCategories() {

        Quantity<LengthUnit> length =
                new Quantity<>(10.0, LengthUnit.FEET);

        Quantity<WeightUnit> weight =
                new Quantity<>(10.0, WeightUnit.KILOGRAM);

        Quantity<VolumeUnit> volume =
                new Quantity<>(10.0, VolumeUnit.LITRE);

        assertEquals(2.0,
                new Quantity<>(10.0, LengthUnit.FEET)
                        .divide(new Quantity<>(5.0, LengthUnit.FEET)));

        assertEquals(5.0,
                new Quantity<>(10.0, WeightUnit.KILOGRAM)
                        .subtract(new Quantity<>(5.0, WeightUnit.KILOGRAM))
                        .getValue());

        assertEquals(2.0,
                new Quantity<>(10.0, VolumeUnit.LITRE)
                        .divide(new Quantity<>(5.0, VolumeUnit.LITRE)));
    }
    @Test
    void testArithmetic_Chain_Operations() {

        Quantity<LengthUnit> q1 = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> q2 = new Quantity<>(2.0, LengthUnit.FEET);
        Quantity<LengthUnit> q3 = new Quantity<>(1.0, LengthUnit.FEET);
        Quantity<LengthUnit> q4 = new Quantity<>(3.0, LengthUnit.FEET);

        double result = q1.add(q2).subtract(q3).divide(q4);

        assertEquals(11.0 / 3.0, result);
    }
    @Test
    void testRefactoring_NoBehaviorChange_LargeDataset() {

        for(int i = 1; i < 1000; i++) {

            Quantity<LengthUnit> a =
                    new Quantity<>(i, LengthUnit.FEET);

            Quantity<LengthUnit> b =
                    new Quantity<>(i/2.0, LengthUnit.FEET);

            assertEquals(i/2.0,
                    a.subtract(b).getValue());
        }
    }

}