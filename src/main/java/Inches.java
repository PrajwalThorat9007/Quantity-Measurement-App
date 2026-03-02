public class Inches {

    private final double value;

    public Inches(double value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object obj) {

        // same reference check
        if (this == obj) return true;

        // null check
        if (obj == null) return false;

        // type check
        if (!(obj instanceof Inches)) return false;

        // value comparison
        Inches other = (Inches) obj;
        return Double.compare(this.value, other.value) == 0;
    }
}