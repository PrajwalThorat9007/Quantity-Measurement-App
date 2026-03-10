/**
 * Generic quantity class supporting multiple measurement categories.
 * Works with any unit enum implementing IMeasurable.
 *
 * Supports:
 * Equality comparison
 * Unit conversion
 * Addition
 * Subtraction
 * Division (dimensionless)
 *
 * UC13 refactors arithmetic logic into a centralized helper method.
 */
public class Quantity<U extends IMeasurable> {

    private final double value;
    private final U unit;

    private static final double EPSILON = 1e-6;

    /**
     * Arithmetic operation types used by helper method.
     */
    private enum ArithmeticOperation {
        ADD,
        SUBTRACT,
        DIVIDE
    }

    /**
     * Constructor.
     */
    public Quantity(double value, U unit) {

        if (unit == null)
            throw new IllegalArgumentException("Unit cannot be null");

        if (!Double.isFinite(value))
            throw new IllegalArgumentException("Value must be finite");

        this.value = value;
        this.unit = unit;
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    /**
     * Centralized arithmetic helper method (UC13).
     */
    private double performOperation(
            Quantity<U> other,
            ArithmeticOperation operation) {

        if (other == null)
            throw new IllegalArgumentException("Operand cannot be null");

        if (this.unit.getClass() != other.unit.getClass())
            throw new IllegalArgumentException("Different measurement categories");

        if (!Double.isFinite(this.value) || !Double.isFinite(other.value))
            throw new IllegalArgumentException("Values must be finite");

        double base1 = this.unit.convertToBaseUnit(this.value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        switch (operation) {

            case ADD:
                return base1 + base2;

            case SUBTRACT:
                return base1 - base2;

            case DIVIDE:
                if (base2 == 0)
                    throw new ArithmeticException("Division by zero");
                return base1 / base2;

            default:
                throw new IllegalArgumentException("Unsupported operation");
        }
    }

    // ───────────────── ADDITION ─────────────────

    public Quantity<U> add(Quantity<U> other) {

        double baseResult = performOperation(other, ArithmeticOperation.ADD);

        double result = unit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, unit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult = performOperation(other, ArithmeticOperation.ADD);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // ───────────────── SUBTRACTION ─────────────────

    public Quantity<U> subtract(Quantity<U> other) {

        double baseResult = performOperation(other, ArithmeticOperation.SUBTRACT);

        double result = unit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, unit);
    }

    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseResult = performOperation(other, ArithmeticOperation.SUBTRACT);

        double result = targetUnit.convertFromBaseUnit(baseResult);

        result = Math.round(result * 100.0) / 100.0;

        return new Quantity<>(result, targetUnit);
    }

    // ───────────────── DIVISION ─────────────────

    /**
     * Returns dimensionless ratio.
     */
    public double divide(Quantity<U> other) {

        return performOperation(other, ArithmeticOperation.DIVIDE);
    }

    // ───────────────── CONVERSION ─────────────────

    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null)
            throw new IllegalArgumentException("Target unit cannot be null");

        double baseValue = unit.convertToBaseUnit(value);

        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);

        return new Quantity<>(convertedValue, targetUnit);
    }

    // ───────────────── EQUALITY ─────────────────

    @Override
    public boolean equals(Object obj) {

        if (this == obj)
            return true;

        if (!(obj instanceof Quantity<?>))
            return false;

        Quantity<?> other = (Quantity<?>) obj;

        if (this.unit.getClass() != other.unit.getClass())
            return false;

        double base1 = unit.convertToBaseUnit(value);
        double base2 = other.unit.convertToBaseUnit(other.value);

        return Math.abs(base1 - base2) < EPSILON;
    }

    // ───────────────── HASHCODE ─────────────────

    @Override
    public int hashCode() {

        double baseValue = unit.convertToBaseUnit(value);

        return Double.hashCode(Math.round(baseValue * 1e6) / 1e6);
    }

    // ───────────────── STRING REPRESENTATION ─────────────────

    @Override
    public String toString() {
        return value + " " + unit.getUnitName();
    }
}