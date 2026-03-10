/**
 * Standalone enum representing weight units.
 * Responsible for all weight unit conversion logic.
 * Base unit is KILOGRAM (conversionFactor = 1.0).
 */
public enum WeightUnit implements IMeasurable {

    KILOGRAM(1.0),
    GRAM(0.001),          // 1 gram = 0.001 kilogram
    POUND(0.453592);      // 1 pound = 0.453592 kilogram

    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    @Override
    public String getUnitName() {
        return this.name();
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    /**
     * Converts a value in this unit to the base unit (kilogram).
     * @param value measurement in this unit
     * @return equivalent value in kilograms
     */
    public double convertToBaseUnit(double value) {
        return value * this.conversionFactor;
    }

    /**
     * Converts a value from the base unit (kilogram) to this unit.
     * @param baseValue measurement in kilograms
     * @return equivalent value in this unit
     */
    public double convertFromBaseUnit(double baseValue) {
        return baseValue / this.conversionFactor;
    }

    SupportsArithmetic supportsArithmetic = () -> true;
}