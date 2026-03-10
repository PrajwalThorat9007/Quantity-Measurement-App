/**
 * Standalone enum representing length units.
 * Responsible for all unit conversion logic.
 * Base unit is FEET (conversionFactor = 1.0).
 */
public enum LengthUnit implements IMeasurable {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);   // 1 foot = 30.48 cm

    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in this unit to the base unit (feet).
     * @param value measurement in this unit
     * @return equivalent value in feet
     */
    public double convertToBaseUnit(double value) {
        return value * this.conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    /**
     * Converts a value from the base unit (feet) to this unit.
     * @param baseValue measurement in feet
     * @return equivalent value in this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.conversionFactor;
    }
}