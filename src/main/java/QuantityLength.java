/**
 * Represents a length measurement with a value and unit.
 * Supports equality comparison and unit conversion
 * by normalizing values to a common base unit (feet).
 */
public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    // epsilon for floating point comparison
    private static final double EPSILON = 1e-6;

    /**
     * Creates a QuantityLength with given value and unit.
     * @param value numeric measurement value
     * @param unit  LengthUnit enum constant
     * @throws IllegalArgumentException if unit is null or value is NaN or infinite
     */
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)               throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value))    throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit  = unit;
    }

    /**
     * Converts this length to a target unit and returns new QuantityLength.
     * @param targetUnit unit to convert to
     * @return new QuantityLength in the target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        // convert to base unit then to target unit
        double convertedValue = toBaseUnit() / targetUnit.getConversionFactor();
        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Static conversion method - converts value from source to target unit.
     * @param value      numeric value to convert
     * @param sourceUnit unit of the input value
     * @param targetUnit unit to convert to
     * @return converted numeric value in target unit
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite, not NaN or Infinite");

        // normalize to base unit then convert to target
        double baseValue = value * sourceUnit.getConversionFactor();
        return baseValue / targetUnit.getConversionFactor();
    }

    // convert this instance value to base unit (feet)
    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    /**
     * Compares two QuantityLength objects by converting to base unit.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)                        return true;
        if (obj == null)                        return false;
        if (!(obj instanceof QuantityLength))   return false;

        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    /**
     * Human readable representation of this length.
     */
    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.name());
    }
}