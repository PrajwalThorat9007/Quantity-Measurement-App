/**
 * Represents a length measurement with a value and unit.
 * Delegates all conversion logic to LengthUnit.
 * Responsible only for equality comparison and arithmetic.
 */
public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    private static final double EPSILON = 1e-6;

    /**
     * Creates a QuantityLength with given value and unit.
     * @param value numeric measurement value
     * @param unit  LengthUnit enum constant
     * @throws IllegalArgumentException if unit is null or value is not finite
     */
    public QuantityLength(double value, LengthUnit unit) {
        if (unit == null)            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit  = unit;
    }

    // ── UC6: add with result in unit of first operand ────────────────────

    /**
     * Adds another length. Result is in this object's unit.
     * @param other second length to add
     * @return new QuantityLength with sum in this unit
     */
    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        return addAndConvert(this, other, this.unit);
    }

    // ── UC7: add with explicit target unit ──────────────────────────────

    /**
     * Adds another length with explicit target unit.
     * @param other      second length to add
     * @param targetUnit unit for the result
     * @return new QuantityLength with sum in target unit
     */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null)      throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        return addAndConvert(this, other, targetUnit);
    }

    /**
     * Static add with explicit target unit.
     * @param first      first QuantityLength
     * @param second     second QuantityLength
     * @param targetUnit unit for the result
     * @return new QuantityLength with sum in target unit
     */
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        if (first == null || second == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)              throw new IllegalArgumentException("Target unit cannot be null");
        return addAndConvert(first, second, targetUnit);
    }

    /**
     * Private helper - delegates to unit methods for conversion.
     * Converts both to base unit, adds, converts to target unit.
     */
    private static QuantityLength addAndConvert(QuantityLength first,
                                                QuantityLength second,
                                                LengthUnit targetUnit) {
        // delegate to unit for base unit conversion
        double sumInBase   = first.unit.convertToBaseUnit(first.value)
                + second.unit.convertToBaseUnit(second.value);

        // delegate to unit for converting back to target unit
        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
        resultValue        = Math.round(resultValue * 100.0) / 100.0;

        return new QuantityLength(resultValue, targetUnit);
    }

    /**
     * Converts this length to a target unit.
     * Delegates conversion to LengthUnit methods.
     * @param targetUnit unit to convert to
     * @return new QuantityLength in target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        // delegate to unit methods
        double baseValue     = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Static conversion utility.
     * Delegates to unit methods.
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        // delegate to unit methods
        double baseValue = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * Compares two lengths by delegating conversion to unit methods.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)                      return true;
        if (obj == null)                      return false;
        if (!(obj instanceof QuantityLength)) return false;

        QuantityLength other = (QuantityLength) obj;

        // delegate to unit for base unit conversion
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.name());
    }

    public LengthUnit getUnit()  { return unit; }
    public double getValue()     { return value; }
}