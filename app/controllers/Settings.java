package controllers;

import models.Member;
import play.Logger;
import play.mvc.Controller;

public class Settings extends Controller {

    public static void index() {
        Logger.info("Rendering Settings");
        Member member = Accounts.getLoggedInMember();
        render("settings.html", member);
    }

    public static void editMember(String firstName, String lastName, String email, String gender,
                                  String password, String address, double height, double startWeight) {
        Member member = Accounts.getLoggedInMember();
        member.setFirstName(firstName);
        member.setLastName(lastName);
        member.setEmail(email);
        member.setGender(gender);
        member.setPassword(password);
        member.setAddress(address);
        member.setHeight(height);
        member.setStartWeight(startWeight);
        member.save();
        Logger.info("Updating user settings for" + member.getFirstName());
        redirect("/dashboard");
    }
}
