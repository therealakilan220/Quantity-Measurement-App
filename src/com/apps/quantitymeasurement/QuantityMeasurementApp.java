package com.apps.quantitymeasurement;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * ---------------------------------------------------------
 * PART 1: THE CORE LOGIC (Length.java)
 * ---------------------------------------------------------
 */
class Length {
    private final double value;
    private final LengthUnit unit;

    // Enum simplifies adding new units like YARDS or CM later
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        this.value = value;
        this.unit = unit;
    }

    // Converts the current value to the base unit (Inches)
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    // Comparison logic based on the converted base values
    public boolean compare(Length thatLength) {
        if (thatLength == null) return false;
        return Double.compare(this.convertToBaseUnit(), thatLength.convertToBaseUnit()) == 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        return this.compare(length);
    }
}

/**
 * ---------------------------------------------------------
 * PART 2: THE APPLICATION (QuantityMeasurementApp.java)
 * ---------------------------------------------------------
 */
class QuantityMeasurementApp {

    public static void demonstrateFeetInchesComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);

        System.out.println("--- UC3: Unified Comparison ---");
        System.out.println("Comparing 1.0 Feet to 12.0 Inches...");
        System.out.println("Are they equal? " + feet.equals(inches));
    }

    public static void main(String[] args) {
        demonstrateFeetInchesComparison();
    }
}

/**
 * ---------------------------------------------------------
 * PART 3: THE TEST SUITE (QuantityMeasurementAppTest.java)
 * ---------------------------------------------------------
 */
class QuantityMeasurementAppTest {

    @Test
    public void testFeetInchesComparison_ShouldReturnTrue() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(12.0, Length.LengthUnit.INCHES);
        // This confirms 1 foot is successfully compared to 12 inches
        assertEquals(feet, inches);
    }

    @Test
    public void testFeetEquality_SameValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.FEET), new Length(1.0, Length.LengthUnit.FEET));
    }

    @Test
    public void testInchesEquality_SameValue() {
        assertEquals(new Length(1.0, Length.LengthUnit.INCHES), new Length(1.0, Length.LengthUnit.INCHES));
    }

    @Test
    public void testCrossUnitInequality() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        Length inches = new Length(1.0, Length.LengthUnit.INCHES);
        assertNotEquals(feet, inches); // 1ft is not 1in
    }

    @Test
    public void testNullComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertFalse(feet.equals(null));
    }

    @Test
    public void testDifferentClassComparison() {
        Length feet = new Length(1.0, Length.LengthUnit.FEET);
        assertNotEquals(feet, new Object());
    }
}