package controllers;

import play.Logger;
import play.mvc.Controller;

public class Accounts extends Controller {

    public static void singup() {
        render("singup.html");
    }

    public static void login() {
        render("login.html");
    }
}
