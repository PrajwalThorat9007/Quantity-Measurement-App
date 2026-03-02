public class QuantityMeasurementApp {

    // static method to compare two feet values
    public static boolean compareFeet(double value1, double value2) {
        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);
        return feet1.equals(feet2);
    }

    // static method to compare two inches values
    public static boolean compareInches(double value1, double value2) {
        Inches inch1 = new Inches(value1);
        Inches inch2 = new Inches(value2);
        return inch1.equals(inch2);
    }

    public static void main(String[] args) {

        // feet comparison
        System.out.println("1.0 ft and 1.0 ft : " + compareFeet(1.0, 1.0));
        System.out.println("1.0 ft and 2.0 ft : " + compareFeet(1.0, 2.0));

        // inches comparison
        System.out.println("1.0 in and 1.0 in : " + compareInches(1.0, 1.0));
        System.out.println("1.0 in and 2.0 in : " + compareInches(1.0, 2.0));
    }
}