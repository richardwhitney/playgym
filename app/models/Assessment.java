package models;

import org.joda.time.DateTime;
import play.db.jpa.Model;

import javax.persistence.Entity;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
public class Assessment extends Model implements Comparable<Assessment> {

    private Date date;
    private double weight;
    private double chest;
    private double thigh;
    private double upperArm;
    private double waist;
    private double hips;
    private int trend;
    private String comment;

    public Assessment(double weight, double chest, double thigh, double upperArm,
                      double waist, double hips, String comment) {
        date = new Date();
        this.weight = weight;
        this.chest = chest;
        this.thigh = thigh;
        this.upperArm = upperArm;
        this.waist = waist;
        this.hips = hips;
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public String getDateFormatted() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        if (getDate() != null) {
            return dateFormat.format(getDate());
        }
        return " ";
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double getChest() {
        return chest;
    }

    public void setChest(double chest) {
        this.chest = chest;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getUpperArm() {
        return upperArm;
    }

    public void setUpperArm(double upperArm) {
        this.upperArm = upperArm;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public int getTrend() {
        return trend;
    }

    public void setTrend(int trend) {
        this.trend = trend;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int compareTo(Assessment assessment) {
        if (getDate() == null || assessment.getDate() == null) {
            return 0;
        }
        return assessment.getDate().compareTo(getDate());
    }

}
