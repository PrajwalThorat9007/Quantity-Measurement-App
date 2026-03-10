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

}