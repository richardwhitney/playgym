package utility;

import models.Assessment;
import models.Member;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class GymUtility {

    public static double calculateBMI(Member member, Assessment assessment) {
        double bmi = assessment.getWeight() / (member.getHeight() * member.getHeight());
        return bmi;
    }

    public static double round(double value , int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static String determineBMICategory(double bmiValue) {
        if (bmiValue < 16) {
            return "SEVERELY UNDERWEIGHT";
        }
        else if (bmiValue >= 16 && bmiValue < 18.5) {
            return "UNDERWEIGHT";
        }
        else if (bmiValue >= 18.5 && bmiValue < 25) {
            return "NORMAL";
        }
        else if (bmiValue >= 25 && bmiValue < 30) {
            return "OVERWEIGHT";
        }
        else if (bmiValue >= 30 && bmiValue < 35) {
            return "MODERATELY OBESE";
        }
        else {
            return "SEVERELY OBESE";
        }
    }

    public static boolean isIdealBodyWeight(Member member, Assessment assessment) {
        // Convert member's height in meters to inches
        double heightInInches = metersToInches(member.getHeight());

        // Ideal weight starts at 50kg for males and 45kg for females
        double idealWeight;
        double actualWeight = assessment.getWeight();
        // If member is male
        if (member.getGender().equals("M")) {
            idealWeight = 50.0f;
        }
        // Member is either female or gender is not specified
        else {
            idealWeight = 45.5f;
        }
        // If member is over 5ft tall
        if (heightInInches > 60) {
            double inchesOverFiveFeet = heightInInches - 60;
            // Add 2.3kg for ever inch over 5ft
            idealWeight += inchesOverFiveFeet * 2.3f;
        }

        // Allow for +/- 0.2 margin of error
        if (actualWeight > idealWeight - 0.2 && actualWeight < idealWeight + 0.2) {
            return true;
        }
        else {
            return false;
        }
    }

    public static double metersToInches(double meters) {
        double inches = meters/0.0254f;
        return inches;
    }
}
