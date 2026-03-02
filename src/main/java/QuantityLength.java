/**
 * Represents a length measurement with a value and unit.
 * Supports equality comparison, unit conversion, and addition.
 * All arithmetic is performed via base unit (feet) normalization.
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

    /**
     * Adds two QuantityLength objects and returns result in unit of first operand.
     * @param other second length to add
     * @return new QuantityLength with sum expressed in this object's unit
     * @throws IllegalArgumentException if other is null
     */
    public QuantityLength add(QuantityLength other) {
        // null check
        if (other == null) throw new IllegalArgumentException("Operand cannot be null");

        // convert both to base unit and add
        double sumInBase = this.toBaseUnit() + other.toBaseUnit();

        // convert result back to this object's unit
        double resultValue = sumInBase / this.unit.getConversionFactor();
        return new QuantityLength(resultValue, this.unit);
    }

    /**
     * Static add method - adds two lengths and returns result in target unit.
     * @param first      first QuantityLength
     * @param second     second QuantityLength
     * @param targetUnit unit of the result
     * @return new QuantityLength with sum in target unit
     */
    public static QuantityLength add(QuantityLength first, QuantityLength second, LengthUnit targetUnit) {
        // null checks
        if (first == null || second == null) throw new IllegalArgumentException("Operands cannot be null");
        if (targetUnit == null)              throw new IllegalArgumentException("Target unit cannot be null");

        // add both base unit values
        double sumInBase = first.toBaseUnit() + second.toBaseUnit();

        // convert to target unit
        double resultValue = sumInBase / targetUnit.getConversionFactor();
        return new QuantityLength(resultValue, targetUnit);
    }

    /**
     * Converts this length to a target unit and returns new QuantityLength.
     * @param targetUnit unit to convert to
     * @return new QuantityLength in the target unit
     */
    public QuantityLength convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double convertedValue = toBaseUnit() / targetUnit.getConversionFactor();
        return new QuantityLength(convertedValue, targetUnit);
    }

    /**
     * Static conversion utility.
     * @param value      value to convert
     * @param sourceUnit source unit
     * @param targetUnit target unit
     * @return converted numeric value
     */
    public static double convert(double value, LengthUnit sourceUnit, LengthUnit targetUnit) {
        if (sourceUnit == null || targetUnit == null)
            throw new IllegalArgumentException("Units cannot be null");
        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");
        double baseValue = value * sourceUnit.getConversionFactor();
        return baseValue / targetUnit.getConversionFactor();
    }

    // convert this instance value to base unit (feet)
    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    public LengthUnit getUnit() {
        return unit;
    }

    public double getValue() {
        return value;
    }

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