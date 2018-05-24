package controllers;

import models.Assessment;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {

    public static void index() {
      Logger.info("Rendering Dashboard");
      List<Assessment> assessments = Assessment.findAll();
      render ("dashboard.html", assessments);
    }

    public static void addAssessment(double weight, double chest, double thigh, double upperArm, double waist, double hips) {
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips);
        assessment.save();
        Logger.info("Adding Assessment");
        redirect("/dashboard");
    }
}
