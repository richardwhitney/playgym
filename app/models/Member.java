package models;

import play.db.jpa.Model;
import utility.GymUtility;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
public class Member extends Model {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String gender;
    private double height;
    private double startWeight;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Assessment> assessments = new ArrayList<Assessment>();

    public Member(String firstName, String lastName, String email, String password,
                  String address, String gender, double height, double startWeight) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.address = address;
        this.gender = gender;
        this.height = height;
        this.startWeight = startWeight;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getStartWeight() {
        return startWeight;
    }

    public void setStartWeight(double startWeight) {
        this.startWeight = startWeight;
    }

    public List<Assessment> getAssessments() {
        return assessments;
    }

    public List<Assessment> getSortedAssessments() {
        assessmentProgressByWeight();
        return assessments;
    }

    public int numberOfAssessments() {
        return assessments.size();
    }

    public boolean hasAssessment() {
        if (assessments.size() > 0) {
            return true;
        }
        return false;
    }

    public void assessmentProgressByWeight() {
        Collections.sort(assessments);
        for (int i = 0; i < assessments.size(); i++) {
            if (i == assessments.size()-1) {
                assessments.get(i).setTrend(0);
            }
            else {
                if (GymUtility.compareAssessmentByWeight(assessments.get(i), assessments.get(i+1)) == 1) {
                    assessments.get(i).setTrend(1);
                }
                else if (GymUtility.compareAssessmentByWeight(assessments.get(i), assessments.get(i+1)) == -1) {
                    assessments.get(i).setTrend(-1);
                }
                else {
                    assessments.get(i).setTrend(0);
                }
            }
        }
    }

    public Assessment getLatestAssessment() {
        Collections.sort(assessments);
        if (assessments.size() > 0) {
            return assessments.get(0);
        }
        return null;
    }

    public static Member findByEmail(String email) {
        return find("email", email).first();
    }

    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

}
