public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // feet to feet comparison
        QuantityLength feet1 = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength feet2 = new QuantityLength(1.0, LengthUnit.FEET);
        System.out.println("1.0 feet == 1.0 feet  : " + feet1.equals(feet2));

        // inches to inches comparison
        QuantityLength inch1 = new QuantityLength(1.0, LengthUnit.INCHES);
        QuantityLength inch2 = new QuantityLength(1.0, LengthUnit.INCHES);
        System.out.println("1.0 inch == 1.0 inch  : " + inch1.equals(inch2));

        // feet to inches cross unit comparison
        QuantityLength oneFoot   = new QuantityLength(1.0, LengthUnit.FEET);
        QuantityLength twelveInch = new QuantityLength(12.0, LengthUnit.INCHES);
        System.out.println("1.0 feet == 12.0 inch : " + oneFoot.equals(twelveInch));
    }
}