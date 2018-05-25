package controllers;

import models.Assessment;
import models.Member;
import play.Logger;
import play.mvc.Controller;

import java.util.List;

public class MemberCtrl extends Controller {

    public static void index(Long id) {
        Member member = Member.findById(id);
        Logger.info("Member id = " + id);
        render("member.html", member);
    }

    public static void updateComment(Long memberid, Long assessmentid, String comment) {
        Member member = Member.findById(memberid);
        Assessment assessment = Assessment.findById(assessmentid);
        assessment.setComment(comment);
        member.save();
        Logger.info("Updating comment on assessment id:" + assessment.getId());
        render("member.html", member);

    }
}
