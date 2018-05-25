package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class Dashboard extends Controller {

    public static void index() {
        Logger.info("Rendering Dashboard");
        Member member = Accounts.getLoggedInMember();
        List<Assessment> assessments = member.getAssessments();
        Assessment assessment = member.getLatestAssessment();
        render ("dashboard.html", member, assessments);
    }

    public static void addAssessment(double weight, double chest, double thigh,
                                     double upperArm, double waist, double hips, String comment) {
        Member member = Accounts.getLoggedInMember();
        Assessment assessment = new Assessment(weight, chest, thigh, upperArm, waist, hips, comment);
        member.assessments.add(assessment);
        member.save();
        Logger.info("Adding Assessment");
        redirect("/dashboard");
    }
}
