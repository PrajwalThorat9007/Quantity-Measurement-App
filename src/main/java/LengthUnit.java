public enum LengthUnit {

    FEET(1.0),      // base unit
    INCHES(1.0 / 12.0); // 12 inches = 1 feet

    // conversion factor relative to feet
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }
}