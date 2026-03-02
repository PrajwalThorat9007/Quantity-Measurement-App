public class QuantityLength {

    private final double value;
    private final LengthUnit unit;

    public QuantityLength(double value, LengthUnit unit) {
        // null unit check
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit  = unit;
    }

    // convert value to base unit (feet)
    private double toBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object obj) {

        // same reference check
        if (this == obj) return true;

        // null check
        if (obj == null) return false;

        // type check
        if (!(obj instanceof QuantityLength)) return false;

        // compare both values in base unit (feet)
        QuantityLength other = (QuantityLength) obj;
        return Double.compare(this.toBaseUnit(), other.toBaseUnit()) == 0;
    }
}