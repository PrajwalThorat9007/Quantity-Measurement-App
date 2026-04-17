package core;

import java.util.function.Function;

public enum     TemperatureUnit implements IMeasurable {

    CELSIUS(
            c -> c,
            c -> c
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,
            c -> (c * 9 / 5) + 32
    );

    private final Function<Double, Double> toCelsius;
    private final Function<Double, Double> fromCelsius;

    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(Function<Double, Double> toCelsius,
                    Function<Double, Double> fromCelsius) {

        this.toCelsius = toCelsius;
        this.fromCelsius = fromCelsius;
    }

    @Override
    public double getConversionFactor() {
        return 1;
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toCelsius.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromCelsius.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public void validateOperationSupport(String operation) {

        if (!supportsArithmetic.isSupported()) {

            if(operation.equals("ADD") ||
                    operation.equals("SUBTRACT") ||
                    operation.equals("DIVIDE")) {

                throw new UnsupportedOperationException(
                        "Arithmetic operations not supported for Temperature");
            }
        }
    }
}