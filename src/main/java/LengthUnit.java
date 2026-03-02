public enum LengthUnit {

    FEET(1.0),                        // base unit
    INCHES(1.0 / 12.0),              // 12 inches = 1 feet
    YARDS(3.0),                       // 1 yard = 3 feet
    CENTIMETERS(0.393701 / 12.0);    // 1 cm = 0.393701 inches = 0.393701/12 feet

    // conversion factor relative to feet
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}