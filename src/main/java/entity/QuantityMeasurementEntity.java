package entity;

public class QuantityMeasurementEntity {

    private double value;
    private String unit;
    private String operation;

    // ✅ Constructor
    public QuantityMeasurementEntity(double value, String unit, String operation) {
        this.value = value;
        this.unit = unit;
        this.operation = operation;
    }

    // ✅ GETTERS (THIS IS WHAT YOU ARE MISSING)

    public double getValue() {
        return value;
    }

    public String getUnit() {
        return unit;
    }

    public String getOperation() {
        return operation;
    }

    // Optional (good for printing)
    @Override
    public String toString() {
        return value + " " + unit + " (" + operation + ")";
    }
}