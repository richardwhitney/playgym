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
}
