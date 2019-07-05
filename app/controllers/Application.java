package controllers;

import models.AgentsAccounts;
import org.mindrot.jbcrypt.BCrypt;
import play.*;
import play.data.DynamicForm;
import play.mvc.*;

import views.html.*;

import java.util.Currency;
import java.util.Set;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static  Result agentSignup(){

        return ok(signup_agent.render("welcome"));
    }

    public static  Result agentSingin(){

        return ok(signin_agent.render("welcome"));
    }


    public static Result agentSignupCreate(){
        DynamicForm signupForm = new DynamicForm().bindFromRequest();
        AgentsAccounts user = new AgentsAccounts();
        user.fullname =signupForm.field("names").value();
        user.gender =signupForm.field("gender").value();
        user.email =signupForm.field("email").value();
        String Texpassword =signupForm.field("password").value();
        user.password = BCrypt.hashpw(Texpassword, BCrypt.gensalt());

        String email = signupForm.get("email");
        if ((user.isEmailExist(email) ) == null){
            user.save();
            flash("success", "successfully registered, go back to login");
            return ok(views.html.signup_agent.render("success"));
        }
        else {

            flash("error", "User with email : " +email+ " aleready exist");
        return badRequest(signup_agent.render("error"));
    }
    }

    // login
    public static Result agentSigninCreate(){

        // get form's email and password
        DynamicForm myloginForm = new DynamicForm().bindFromRequest();
        String email = myloginForm.get("email");
        String Inpassword = myloginForm.get("password");


        //Access agentaccount model
        AgentsAccounts agentaccount  = new AgentsAccounts();



        // fetch data from agent table using input email method
         AgentsAccounts user = agentaccount.isEmailExist(email);


        // Decrypt hashed password and compare it to input password
        // check if email exist and if password match then return fetched id then continue
           if ((agentaccount.isEmailExist(email))!=null  && BCrypt.checkpw(Inpassword, user.password)){
               long myid = user.id;
               String id =  Long.toString(myid);

               // Clear existing session
              session().clear();

           //    create new session
              session("agentlog", id);

               return  redirect("/application/agent");
           }
         else {

             flash("error", "Invalid email or password ");
     //             flash("error", "Invalid email or password <a href=\"" + routes.Application.agentSingin().url()+"\" class=\"btn btn-link\">Log in</a>");
             return  ok(signin_agent.render("error"));
           }
    }

    public  static Result Apply(){
        Set<Currency> availableCurrencies = Currency.getAvailableCurrencies();

    return ok(application_agent.render(""));
    }


    /**
     * Logout and clean the session.
     */
    public static Result logout() {
        session().clear();
        flash("success", "You've been logged out");
        return Results.redirect("/agent/logout");
    }


}
