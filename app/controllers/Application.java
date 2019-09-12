package controllers;

import akka.util.ByteString;
import com.fasterxml.jackson.databind.JsonNode;
import models.*;
import org.mindrot.jbcrypt.BCrypt;
import play.*;
import play.api.libs.Files;
import play.api.mvc.Session;
import play.data.DynamicForm;
import play.libs.Json;
import play.mvc.*;

import scala.concurrent.stm.Source;
import scala.xml.Null;
import scalax.file.Path;
import scalax.io.support.FileUtils;
import views.html.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;

import static org.joda.time.format.ISODateTimeFormat.time;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render());
    }

    public static  Result agentSignup(){

        return ok(signup_agent.render("welcome"));
    }

      // Display agent signin form
    public static  Result agentSingin(){

        // Find user session
        boolean Session =  session().get("agentlog") !=null;

        if(Session){

            String ExistingSession = session().get("agentlog");
           AgentApplication applicant  =AgentApplication.application(ExistingSession);
            boolean applied = applicant !=null;

            if (applied){    // When student has loggedin and applied

                return redirect("/applied/agent");
            }
            else{     // When student has loggedin and has not applied

                return redirect("/application/agent");

            }

        }else{
            return ok(signin_agent.render("welcome"));
        }
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
            session().clear();
            return ok(views.html.signup_agent.render("success"));
        }
        else {

            flash("error", "User with email : " +email+ " aleready exist");
        return badRequest(signup_agent.render("error"));
    }
    }

    // login for agent  function
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

//               return  redirect("/application/agent");
               return redirect("/applied/agent");


           }
         else {

             flash("error", "Invalid email or password ");
     //             flash("error", "Invalid email or password <a href=\"" + routes.Application.agentSingin().url()+"\" class=\"btn btn-link\">Log in</a>");
             return  ok(signin_agent.render("error"));
           }
    }



    public  static Result Apply(){

    return ok(application_agent.render(""));
    }


    // Student application form submit
    public  static Result ApplicationSubmit(){

        String ExistingSession = session().get("agentlog");
        AgentApplication Isapplicant  =AgentApplication.application(ExistingSession);

        String phone =null;
        String nid =null;

       List<AgentApplication> application = AgentApplication.findApplication.all();

            boolean Hasapplied = Isapplicant !=null;

        DynamicForm applicationForm = new DynamicForm().bindFromRequest();
        AgentApplication applicant = new AgentApplication();
        String agentid = session("agentlog").toString();
        applicant.agent = AgentsAccounts.FindAgent.byId(Long.parseLong(agentid));

        String districtid = applicationForm.field("district").value();
        applicant.district = District.InfoDistricts.byId(Long.parseLong(districtid));

        String sectorid = applicationForm.field("sector").value();
        applicant.sector = Sector.InfoSectors.byId(Long.parseLong(sectorid));

        String cellid = applicationForm.field("cell").value();
        applicant.cell = Cell.InfoCell.byId(Long.parseLong(cellid));
        applicant.agromeration = applicationForm.field("agglomeration").value();
        applicant.firstname = applicationForm.field("firstname").value();
        applicant.lastname = applicationForm.field("lastname").value();
        applicant.phonenumber = applicationForm.field("phone").value();
        applicant.address = applicationForm.field("address").value();
        applicant.address = applicationForm.field("address").value();
        applicant.nationality = applicationForm.field("nationality").value();
        applicant.nid = applicationForm.field("nationalid").value();
        applicant.company = applicationForm.field("company").value();
        applicant.sitename = applicationForm.field("sitename").value();
        int status =0;
        applicant.reject_status = Integer.toString(status);

        Http.MultipartFormData formFile = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadedFile = formFile.getFile("passport");

        // get file name from uploaded
        String fileName = uploadedFile.getFilename();
        File SourceUploadedfile = uploadedFile.getFile();
        //Rename a file
        String photoname = (new Date()).getTime() +"_"+ fileName;
            File DestinationFilespath = new File("public/uploads/"+ photoname);
            applicant.passportphoto = new File("/uploads/"+ photoname).getPath() ;

        if (Hasapplied){
              // after find that  user application is  already there
              flash("error","applicant already exist");
              return  badRequest(application_agent.render("error"));
          }
          else if (Isapplicant !=null){

              // After find that applications exist and check among it if there is not used phone number or  national Id
              phone = Isapplicant.phonenumber;
              nid = Isapplicant.nid;

                 if (!phone.equals("") && phone == applicationForm.get("phone") ){
                  flash("error","Phone number already used");
                  return  badRequest(application_agent.render("error"));
              }
              else if ( !nid.equals("") && nid == applicationForm.get("nid")){
                  flash("error","Your National ID number already used");
                  return  badRequest(application_agent.render("error"));
              }

             }
         else {

//            If user application is absent and phone and National Id were not used

              flash("success","Thank you, your application was successfully received, we shall reply you shortly <a href=\"" + routes.Application.AppliedAgent().url()+"\" class=\"btn btn-link\"> Next </a>");

            SourceUploadedfile.renameTo(DestinationFilespath); //here you are moving photo to path directory
            applicant.save();
              return ok(application_agent.render("success"));
          }
        return ok();

    }

//  User who is already applied

  public static Result AppliedAgent(){

      String ExistingSession = session().get("agentlog");
      AgentApplication Isapplicant  =AgentApplication.application(ExistingSession);
      String firstname = null;
      String lastname = null;
      String status = null;
      String address ;

      // check if applicant details is existing
      if (Isapplicant!=null) {
          firstname = Isapplicant.firstname;
          lastname = Isapplicant.lastname;
          status = Isapplicant.reject_status;
          address = Isapplicant.address;
          List<ApprovedAgents> myagent = ApprovedAgents.approved();
          Boolean IsApproved = myagent != null;

          if (status.equals("approved")){


              List<AgentApplication> contacts = AgentApplication.applicationsList(ExistingSession);


              flash("approval", "Your Application was approved as <b> Active agent</b> ");
              System.out.println(IsApproved);
              return ok(approvedagent_application.render("approval", contacts));
          } else if (!status.equals("") && status.equals("rejected")) {

              flash("rejection", "Dear <b> " + firstname + " " + lastname + "</b>, Sorry Your Application have been rejected ");
              return ok(appliedagent.render("rejection"));
          }
          else
              {

              flash("names", firstname + " " + lastname);
              flash("nondecision", "This time your application is pending, wait for feeback Patiently <a href=\"" + routes.Application.logout().url() + "\" class=\"btn btn-link\">Log out</a>");
              return ok(appliedagent.render("nondecision"));
          }

      }else{

          return redirect(routes.Application.Apply());
      }
  }

    public static List<AgentsInquiry> inquiry;
    //  Chat with interface for user
    public static Result ShowChat(){

        // get the list of all message sent by agent and his /her replies
       inquiry = AgentsInquiry.FindAgentChat(session().get("agentlog"));
        return ok(agent_chat.render("", inquiry));
    }

       // When an agent sends achat
    public static Result SendChat(){
        DynamicForm signupForm = new DynamicForm().bindFromRequest();
        AgentsInquiry user = new AgentsInquiry();

        String agentid = session().get("agentlog");

        user.agent =ApprovedAgents.approved.where().eq("applicant_id",agentid).findUnique();
        user.message =signupForm.field("message").value();
        user.save();
        flash("success"," Your message was sent, thanks " );
        return ok(agent_chat.render("success", inquiry));
    }


    /**
     * Logout and clean the session.
     */
    public static Result logout() {

        session().clear();
        flash("success", "You've been logged out");
        return Results.redirect("/");
    }


    public static Result GetSectors(Long id){
        // get the list of the sectors according to selected district and pass it to json
      return ok(Json.toJson(Sector.ResultSectors(id)));
     }

     public static Result GetCells(Long id){
        // get the list of the cells according to selected sector and pass it to json
      return ok(Json.toJson(Cell.ResultCells(id)));
     }

}
