package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        /**
         * Override equals() method to compare two Feet objects based on their value
         */
        @Override
        public boolean equals(Object obj) {
            // 1. Reference Check: If both references point to the same object
            if (this == obj) return true;

            // 2. Null Check: If the compared object is null
            if (obj == null) return false;

            // 3. Type Check: If the compared object is not of type Feet
            if (getClass() != obj.getClass()) return false;

            // 4. Value Comparison: Use Double.compare() for equality
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void main(String[] args) {
        // Main method for manual testing/demonstration
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        System.out.println("Feet equality check: " + feet1.equals(feet2));
    }
}