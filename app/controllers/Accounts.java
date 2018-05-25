package controllers;

import models.Member;
import models.Trainer;
import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

    public static void singup() {
        render("signup.html");
    }

    public static void login() {
        render("login.html");
    }

    public static void register(String firstName, String lastName, String email, String gender, String password,
                                String address, double height, double startWeight) {
        Logger.info("Registering new user " + email);
        Member member = new Member(firstName, lastName, email, password, address, gender, height, startWeight);
        member.save();
        redirect("/");
    }

    public static void authenticate(String email, String password) {
        Logger.info("Attempting to authenticate with " + email + ": " + password);
        Member member = Member.findByEmail(email);
        Trainer trainer = Trainer.findByEmail(email);
        if ((member != null) && (member.checkPassword(password) == true)) {
            Logger.info("Authentication successful");
            session.put("logged_in_Memberid", member.id);
            redirect("/dashboard");
        }
        else if ((trainer != null) && (trainer.checkPassword(password) == true)) {
            Logger.info("Attempting to authenticate with " + email + ": " + password);
            session.put("logged_in_Trainerid", trainer.id);
            redirect("/trainerdashboard");
        }
        else {
            Logger.info("Authentication failed");
            redirect("/login");
        }
    }

    public static void logout() {
        session.clear();
        redirect("/");
    }

    public static Member getLoggedInMember() {
        Member member = null;
        if (session.contains("logged_in_Memberid")) {
            String memberId = session.get("logged_in_Memberid");
            member = Member.findById(Long.parseLong(memberId));
        }
        else {
            login();
        }
        return member;
    }

    public static Trainer getLoggedInTrainer() {
        Trainer trainer = null;
        if (session.contains("logged_in_Trainerid")) {
            String trainerId = session.get("logged_in_Trainerid");
            trainer = Trainer.findById(Long.parseLong(trainerId));
        }
        else {
            login();
        }
        return trainer;
    }
}
