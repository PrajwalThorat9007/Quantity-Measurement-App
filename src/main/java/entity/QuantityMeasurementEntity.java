package entity;

public class QuantityMeasurementEntity {

    private double value;
    private String unit;
    private String operation;

    public QuantityMeasurementEntity(double value, String unit, String operation) {
        this.value = value;
        this.unit = unit;
        this.operation = operation;
    }

    @Override
    public String toString() {
        return value + " " + unit + " (" + operation + ")";
    }
}