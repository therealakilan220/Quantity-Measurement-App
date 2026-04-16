package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ---------------------------------------------------------
 * PART 1: THE APPLICATION CODE (UC2)
 * ---------------------------------------------------------
 */
class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;
        public Feet(double value) { this.value = value; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Feet other = (Feet) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    // Inner class to represent Inches measurement
    public static class Inches {
        private final double value;
        public Inches(double value) { this.value = value; }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Inches other = (Inches) obj;
            return Double.compare(this.value, other.value) == 0;
        }
    }

    public static void demonstrateFeetEquality() {
        Feet f1 = new Feet(1.0);
        Feet f2 = new Feet(1.0);
        System.out.println("Feet Equality (1.0 vs 1.0): " + f1.equals(f2));
    }

    public static void demonstrateInchesEquality() {
        Inches i1 = new Inches(12.0);
        Inches i2 = new Inches(12.0);
        System.out.println("Inches Equality (12.0 vs 12.0): " + i1.equals(i2));
    }

    public static void main(String[] args) {
        demonstrateFeetEquality();
        demonstrateInchesEquality();
    }
}

/**
 * ---------------------------------------------------------
 * PART 2: THE TEST SUITE (JUnit 5)
 * ---------------------------------------------------------
 */
class QuantityMeasurementAppTest {

    // --- Feet Equality Tests ---
    @Test
    public void testFeetEquality_SameValue() {
        assertEquals(new QuantityMeasurementApp.Feet(1.0), new QuantityMeasurementApp.Feet(1.0));
    }

    @Test
    public void testFeetEquality_DifferentValue() {
        assertNotEquals(new QuantityMeasurementApp.Feet(1.0), new QuantityMeasurementApp.Feet(2.0));
    }

    @Test
    public void testFeetEquality_NullComparison() {
        assertFalse(new QuantityMeasurementApp.Feet(1.0).equals(null));
    }

    @Test
    public void testFeetEquality_DifferentClass() {
        assertNotEquals(new QuantityMeasurementApp.Feet(1.0), new Object());
    }

    @Test
    public void testFeetEquality_SameReference() {
        QuantityMeasurementApp.Feet feet = new QuantityMeasurementApp.Feet(1.0);
        assertTrue(feet.equals(feet));
    }

    // --- Inches Equality Tests ---
    @Test
    public void testInchesEquality_SameValue() {
        assertEquals(new QuantityMeasurementApp.Inches(12.0), new QuantityMeasurementApp.Inches(12.0));
    }

    @Test
    public void testInchesEquality_DifferentValue() {
        assertNotEquals(new QuantityMeasurementApp.Inches(12.0), new QuantityMeasurementApp.Inches(1.0));
    }

    @Test
    public void testInchesEquality_NullComparison() {
        assertFalse(new QuantityMeasurementApp.Inches(12.0).equals(null));
    }

    @Test
    public void testInchesEquality_DifferentClass() {
        // This confirms that an Inch is NOT a Foot even if values match
        assertNotEquals(new QuantityMeasurementApp.Inches(12.0), new QuantityMeasurementApp.Feet(12.0));
    }

    @Test
    public void testInchesEquality_SameReference() {
        QuantityMeasurementApp.Inches inches = new QuantityMeasurementApp.Inches(12.0);
        assertTrue(inches.equals(inches));
    }
}