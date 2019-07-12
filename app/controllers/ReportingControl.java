package controllers;

import models.AgentApplication;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ReportingControl extends Controller {

    public static Result ListAppicants(){


        return ok(views.html.admin_agentsapproval.render(""));
    }
}
