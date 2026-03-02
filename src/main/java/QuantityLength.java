/**
 * Represents a length measurement with a value and unit.
 * Supports equality, conversion, and addition with explicit target unit.
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
     * Adds another length to this length.
     * Result is expressed in the unit of this (first) operand.
     * @param other second length to add
     * @return new QuantityLength with sum in this object's unit
     */
    public QuantityLength add(QuantityLength other) {
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");
        return addInBaseUnit(this, other, this.unit);
    }

    // ── UC7: add with explicit target unit ──────────────────────────────

    /**
     * Instance method - adds another length with explicit target unit.
     * @param other      second length to add
     * @param targetUnit unit for the result
     * @return new QuantityLength with sum in target unit
     */
    public QuantityLength add(QuantityLength other, LengthUnit targetUnit) {
        if (other == null)      throw new IllegalArgumentException("Operand cannot be null");
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        return addInBaseUnit(this, other, targetUnit);
    }

    /**
     * Static method - adds two lengths with explicit target unit.
     * @param first      first QuantityLength
     * @param second     second QuantityLength
     * @param targetUnit unit for the result
     * @return new QuantityLength with sum in target unit
     */
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        if (first == null || second == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)              throw new IllegalArgumentException("Target unit cannot be null");
        return addInBaseUnit(first, second, targetUnit);
    }

    /**
     * Private helper - converts both to base unit, adds, converts to target.
     * Used by all add() overloads to avoid code duplication.
     * @param first      first QuantityLength
     * @param second     second QuantityLength
     * @param targetUnit unit for the result
     * @return new QuantityLength with sum in target unit
     */
    private static QuantityLength addInBaseUnit(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        // convert both to base unit and sum
        double sumInBase = first.toBaseUnit() + second.toBaseUnit();

        // convert sum to target unit and round to 2 decimal places
        double resultValue = sumInBase / targetUnit.getConversionFactor();
        resultValue = Math.round(resultValue * 100.0) / 100.0;

        return new QuantityLength(resultValue, targetUnit);
    }

    /**
     * Static conversion utility.
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        return (value * sourceUnit.getConversionFactor()) / targetUnit.getConversionFactor();
    }

    /**
     * Instance conversion method.
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double convertedValue = toBaseUnit() / targetUnit.getConversionFactor();
        return new QuantityLength(convertedValue, targetUnit);
    }

    // convert this instance to base unit (feet)
    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    public LengthUnit getUnit()  { return unit; }
    public double getValue()     { return value; }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)                      return true;
        if (obj == null)                      return false;
        if (!(obj instanceof QuantityLength)) return false;
        QuantityLength other = (QuantityLength) obj;
        return Math.abs(this.toBaseUnit() - other.toBaseUnit()) < EPSILON;
    }

    @Override
    public String toString() {
        return String.format("%.6f %s", value, unit.name());
    }
}