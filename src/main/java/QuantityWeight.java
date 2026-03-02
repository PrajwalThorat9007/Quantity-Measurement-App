/**
 * Represents a weight measurement with a value and unit.
 * Delegates all conversion logic to WeightUnit.
 * Responsible only for equality comparison and arithmetic.
 */
public class QuantityWeight {

    private final double value;
    private final WeightUnit unit;

    private static final double EPSILON = 1e-6;

    /**
     * Creates a QuantityWeight with given value and unit.
     * @param value numeric measurement value
     * @param unit  WeightUnit enum constant
     * @throws IllegalArgumentException if unit is null or value is not finite
     */
    public QuantityWeight(double value, WeightUnit unit) {
        if (unit == null)            throw new IllegalArgumentException("Unit cannot be null");
        if (!Double.isFinite(value)) throw new IllegalArgumentException("Value must be finite");
        this.value = value;
        this.unit  = unit;
    }

    // ── Addition: result in first operand unit ───────────────────────────

    /**
     * Adds another weight. Result is in this object's unit.
     * @param other second weight to add
     * @return new QuantityWeight with sum in this unit
     */
    public QuantityWeight add(QuantityWeight other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        return addAndConvert(this, other, this.unit);
    }

    // ── Addition: result in explicit target unit ─────────────────────────

    /**
     * Adds another weight with explicit target unit.
     * @param other      second weight to add
     * @param targetUnit unit for the result
     * @return new QuantityWeight with sum in target unit
     */
    public QuantityWeight add(QuantityWeight other, WeightUnit targetUnit) {
        if (other == null)      throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        return addAndConvert(this, other, targetUnit);
    }

    /**
     * Static add with explicit target unit.
     * @param first      first QuantityWeight
     * @param second     second QuantityWeight
     * @param targetUnit unit for the result
     * @return new QuantityWeight with sum in target unit
     */
    public static QuantityWeight add(QuantityWeight first, QuantityWeight second, WeightUnit targetUnit) {
        if (first == null || second == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)              throw new IllegalArgumentException("Target unit cannot be null");
        return addAndConvert(first, second, targetUnit);
    }

    /**
     * Private helper - delegates to unit methods for conversion.
     * Converts both to base unit, adds, converts to target unit.
     */
    private static QuantityWeight addAndConvert(QuantityWeight first,
                                                QuantityWeight second,
                                                WeightUnit targetUnit) {
        // delegate to unit for base unit conversion
        double sumInBase   = first.unit.convertToBaseUnit(first.value)
                + second.unit.convertToBaseUnit(second.value);

        // convert to target unit and round to 2 decimal places
        double resultValue = targetUnit.convertFromBaseUnit(sumInBase);
        resultValue        = Math.round(resultValue * 100.0) / 100.0;

        return new QuantityWeight(resultValue, targetUnit);
    }

    /**
     * Converts this weight to a target unit.
     * @param targetUnit unit to convert to
     * @return new QuantityWeight in target unit
     */
    public QuantityWeight convertTo(WeightUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");

        // delegate to unit methods
        double baseValue      = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new QuantityWeight(convertedValue, targetUnit);
    }

    /**
     * Static conversion utility.
     */
    public static double convert(double value, WeightUnit sourceUnit, WeightUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        double baseValue = sourceUnit.convertToBaseUnit(value);
        return targetUnit.convertFromBaseUnit(baseValue);
    }

    /**
     * Compares two weights by delegating conversion to unit methods.
     * Weight and length are incompatible — cross category comparison returns false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)                     return true;
        if (obj == null)                     return false;

        // cross category check — weight != length
        if (this.getClass() != obj.getClass()) return false;

        QuantityWeight other = (QuantityWeight) obj;

        // delegate to unit for base unit conversion
        double thisBase  = this.unit.convertToBaseUnit(this.value);
        double otherBase = other.unit.convertToBaseUnit(other.value);

        return Math.abs(thisBase - otherBase) < EPSILON;
    }

    @Override
    public int hashCode() {
        // hash based on base unit value for consistency with equals()
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return Double.hashCode(Math.round(baseValue * 1e6) / 1e6);
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.name());
    }

    public WeightUnit getUnit() { return unit; }
    public double getValue()    { return value; }
}