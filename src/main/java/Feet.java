public class Feet {

    private final double value;

    public Feet(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        // same reference check
        if (this == obj) return true;

        // null check
        if (obj == null) return false;

        // type check
        if (!(obj instanceof Feet)) return false;

        // value comparison
        Feet other = (Feet) obj;
        return Double.compare(this.value, other.value) == 0;
    }
}