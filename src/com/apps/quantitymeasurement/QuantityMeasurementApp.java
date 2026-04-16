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

    public enum LengthUnit {
        // All conversion factors are relative to 1 Inch
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701); // 1 cm ≈ 0.393701 inches

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

    private double convertToBaseUnit() {
        // Rounding to 2 decimal places to handle floating point precision in comparisons
        double converted = this.value * this.unit.getConversionFactor();
        return Math.round(converted * 100.0) / 100.0;
    }

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

    public static boolean demonstrateLengthComparison(double v1, Length.LengthUnit u1, double v2, Length.LengthUnit u2) {
        Length l1 = new Length(v1, u1);
        Length l2 = new Length(v2, u2);
        boolean result = l1.equals(l2);
        System.out.println(v1 + " " + u1 + " == " + v2 + " " + u2 + " : " + result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println("--- UC4: Extended Unit Support ---");
        demonstrateLengthComparison(1.0, Length.LengthUnit.YARDS, 36.0, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(100.0, Length.LengthUnit.CENTIMETERS, 39.3701, Length.LengthUnit.INCHES);
        demonstrateLengthComparison(3.0, Length.LengthUnit.FEET, 1.0, Length.LengthUnit.YARDS);
        demonstrateLengthComparison(30.48, Length.LengthUnit.CENTIMETERS, 1.0, Length.LengthUnit.FEET);
    }
}

/**
 * ---------------------------------------------------------
 * PART 3: THE TEST SUITE (QuantityMeasurementAppTest.java)
 * ---------------------------------------------------------
 */
class QuantityMeasurementAppTest {

    @Test
    public void yardEquals36Inches() {
        assertEquals(new Length(1.0, Length.LengthUnit.YARDS), new Length(36.0, Length.LengthUnit.INCHES));
    }

    @Test
    public void centimeterEquals39Point3701Inches() {
        assertEquals(new Length(100.0, Length.LengthUnit.CENTIMETERS), new Length(39.3701, Length.LengthUnit.INCHES));
    }

    @Test
    public void threeFeetEqualsOneYard() {
        assertEquals(new Length(3.0, Length.LengthUnit.FEET), new Length(1.0, Length.LengthUnit.YARDS));
    }

    @Test
    public void thirtyPoint48CmEqualsOneFoot() {
        // 30.48 * 0.393701 ≈ 11.999... (Rounding in convertToBaseUnit handles this)
        assertEquals(new Length(30.48, Length.LengthUnit.CENTIMETERS), new Length(1.0, Length.LengthUnit.FEET));
    }

    @Test
    public void yardNotEqualToInches() {
        assertNotEquals(new Length(1.0, Length.LengthUnit.YARDS), new Length(1.0, Length.LengthUnit.INCHES));
    }

    @Test
    public void referenceEqualitySameObject() {
        Length yard = new Length(1.0, Length.LengthUnit.YARDS);
        assertTrue(yard.equals(yard));
    }

    @Test
    public void equalsReturnsFalseForNull() {
        assertFalse(new Length(1.0, Length.LengthUnit.FEET).equals(null));
    }

    @Test
    public void reflexiveSymmetricAndTransitiveProperty() {
        Length a = new Length(1.0, Length.LengthUnit.YARDS);
        Length b = new Length(3.0, Length.LengthUnit.FEET);
        Length c = new Length(36.0, Length.LengthUnit.INCHES);

        assertTrue(a.equals(a)); // Reflexive
        assertTrue(a.equals(b) && b.equals(a)); // Symmetric
        assertTrue(a.equals(b) && b.equals(c) && a.equals(c)); // Transitive
    }
}