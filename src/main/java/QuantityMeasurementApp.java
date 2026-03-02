public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // yards to yards
        QuantityLength yard1 = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength yard2 = new QuantityLength(1.0, LengthUnit.YARDS);
        System.out.println("1.0 yard == 1.0 yard      : " + yard1.equals(yard2));

        // yards to feet
        QuantityLength oneYard   = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength threeFeet = new QuantityLength(3.0, LengthUnit.FEET);
        System.out.println("1.0 yard == 3.0 feet      : " + oneYard.equals(threeFeet));

        // yards to inches
        QuantityLength oneYard2      = new QuantityLength(1.0, LengthUnit.YARDS);
        QuantityLength thirtySixInch = new QuantityLength(36.0, LengthUnit.INCHES);
        System.out.println("1.0 yard == 36.0 inches   : " + oneYard2.equals(thirtySixInch));

        // centimeters to centimeters
        QuantityLength cm1 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        QuantityLength cm2 = new QuantityLength(2.0, LengthUnit.CENTIMETERS);
        System.out.println("2.0 cm == 2.0 cm          : " + cm1.equals(cm2));

        // centimeters to inches
        QuantityLength oneCm        = new QuantityLength(1.0, LengthUnit.CENTIMETERS);
        QuantityLength pointThreeIn = new QuantityLength(0.393701, LengthUnit.INCHES);
        System.out.println("1.0 cm == 0.393701 inches : " + oneCm.equals(pointThreeIn));
    }
}