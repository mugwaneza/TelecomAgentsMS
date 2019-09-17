package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import models.*;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import static play.data.Form.form;

public class AdminDashboard extends Controller {
    // Bind form data
    public static  DynamicForm  myform;

    public static Result DashboardIndex() {

        return ok(views.html.admin.render());

    }

       public static Result DashboardProvince() {

        return ok(views.html.adminprovince.render("welcome"));

    }

     public static Result DashboardDistrict() {
        return ok(views.html.admindistrict.render("welcome"));

    }

    public static Result DashboardSector() {

        return ok(views.html.adminsector.render("welcome"));

    }

      public static Result DashboardCell() {

        return ok(views.html.admincell.render("welcome"));

    }

    public static Result DashboardAdminAccounts() {

        return ok(views.html.adminaccounts.render(""));

    }


    public static Result DashboardCreateProvince(){
        myform = form().bindFromRequest();
        Province prov = new Province();
        prov.province = myform.field("province").value();
        prov.description = myform.field("description").value();
        String myprovince = myform.get("province"); 
        if ((prov.isProvinceExist(myprovince)) ==null ){
                    prov.save();

            flash("success", myprovince + " successfully saved " );
            return ok(views.html.adminprovince.render("success"));           }
        else{
    
        flash("error", myprovince + " province already exist " );
            return badRequest(views.html.adminprovince.render("error"));

        }
    }

    public static Result DashboardCreateDistrict(){
        myform = form().bindFromRequest();
        District district = new District();
        String provinceid = myform.field("province").value();
        district.province = Province.InfoProvinces.byId(Long.parseLong(provinceid));
        district.district = myform.field("district").value();
        district.description = myform.field("description").value();
         
        String mydistrict = myform.get("district"); 
        if ((district.isDistrictExist(mydistrict)) ==null ){
                    district.save();

            flash("success", mydistrict + " successfully saved " );
            return ok(views.html.admindistrict.render("success"));
           }
        else{

        flash("error", mydistrict + " district already exist " );
            return badRequest(views.html.admindistrict.render("error"));
        }

    }


    public static Result DashboardCreateSector(){
        myform = form().bindFromRequest();


        Sector sector = new Sector();
        String districtid = myform.field("district").value();
        sector.district = District.InfoDistricts.byId(Long.parseLong(districtid));
        sector.sector = myform.field("sector").value();
        sector.description = myform.field("description").value();
        String mysector  = myform.get("sector"); 
        if ((sector.isSectorExist(mysector)) ==null ){
                    sector.save();
            flash("success", mysector + " successfully saved " );
            return ok(views.html.adminsector.render("success"));
        }
        else{
      
        flash("error", mysector + " sector already exist " );
            return badRequest(views.html.adminsector.render("error"));
       }

    }


    public static Result DashboardCreateCell(){

         myform  = form().bindFromRequest();
        Cell cell = new Cell();
        String sectorid = myform.field("sector").value();
        String mycell = myform.get("cell");
        cell.sector = Sector.InfoSectors.byId(Long.parseLong(sectorid));
        cell.cell = mycell;
        cell.description = myform.field("description").value();
        if ((cell.isCellExist(mycell)) == null){
            cell.save();
            flash("success", mycell + " successfully saved " );
            return ok(views.html.admincell.render("success"));
        }
        else {
            flash("error", mycell + " cell already exist " );
            return badRequest(views.html.admincell.render("error"));
        }

    }


    // View Admin sign function
    public static Result adminLogin(){

            // Find user session
            boolean Session =  session().get("adminlog") !=null;
            if(Session){
            return redirect(routes.AdminDashboard.DashboardIndex());
             }else{
                return ok(signin_admin.render("welcome"));
              }
         }

//         Signin  admin function

    public static Result adminSignCreate(){

        // get form's email and password
        DynamicForm myloginForm = new DynamicForm().bindFromRequest();
        String email = myloginForm.get("email");
        String Inpassword = myloginForm.get("password");

          //Access admin  model
         AdminAccount admin  = new AdminAccount();

          // fetch data from agent table using input email method
         AdminAccount user = admin.isEmailExist(email);

        // Decrypt hashed password and compare it to input password
        // check if email exist and if password match then return fetched id then continue

        if ((admin.isEmailExist(email))!=null  && BCrypt.checkpw(Inpassword, user.password)){

            long myid = user.id;
            String id =  Long.toString(myid);

               // Clear existing session
             session().clear();
                 //create new session
               session("adminlog", id);

            return  redirect("/dashboard");
        }
        else {

            flash("error", "Invalid email or password ");
            return  ok(signin_admin.render("error"));
        }
    }



      // Admin login method
    public static Result adminLoginCreateAcount(){

        DynamicForm signupForm = new DynamicForm().bindFromRequest();
        AdminAccount user = new AdminAccount();
        user.fullname =signupForm.field("fullname").value();
        user.gender =signupForm.field("gender").value();
        user.company =signupForm.field("company").value();
        user.email =signupForm.field("email").value();
        String Texpassword =signupForm.field("password").value();
        user.password = BCrypt.hashpw(Texpassword, BCrypt.gensalt());
        user.address  =signupForm.field("address").value();

        String email = signupForm.get("email");
        if ((user.isEmailExist(email) ) == null){
            user.save();
            flash("success", "successfully registered");
            return ok(views.html.adminaccounts.render("success"));
        }
        else {

            flash("error", "User with email : " +email+ " aleready exist");
            return badRequest(adminaccounts.render("error"));
        }
    }



// Approve agent

    public static Result ApproveAgent(){
        myform  = form().bindFromRequest();
        ApprovedAgents agent = new ApprovedAgents();

        String applicantid = myform.field("userid").value();
        agent.applicant  = AgentApplication.findApplication.byId(Long.parseLong(applicantid));
        String adminid = session().get("adminlog");
        agent.admin = AdminAccount.FindAdmin.byId(Long.parseLong(adminid));
        agent.status = true;
        agent.walletnumber = myform.field("wallet").value();
        agent.airtimenumber = myform.field("airtime").value();
        agent.save();

        // Afteer approve Agent Update reject status in applicant table
        SqlUpdate update = Ebean. createSqlUpdate("UPDATE applicant SET reject_status=:reject_status WHERE id=:id")
                .setParameter("reject_status", "approved")
                .setParameter("id", applicantid);
         int rows = update.execute();
        return redirect(routes.ReportingControl.ListAppicants());
    }

     // Reject applicant

    public static Result RejectApplicant(){
        myform  = form().bindFromRequest();
        String applicantid = myform.field("rejectid").value();
        SqlUpdate update = Ebean. createSqlUpdate("UPDATE applicant SET reject_status=:reject_status WHERE id=:id")
                .setParameter("reject_status", "rejected")
                .setParameter("id", applicantid);
        int rows = update.execute();
        return redirect(routes.ReportingControl.ListAppicants());
     }

    public static Result Inquiries(){
        return ok(admin_agentinquiries.render("", "" ));

    }

    public static Result ChatDetails(String id){
        List<AgentsInquiry> inquiry = AgentsInquiry.agentChat(id);
        String ur = request().uri();
        System.out.println(ur);
        return ok(admin_chatposts.render("", inquiry));
    }

    //    when an admin replies to the agent
    public static Result ReplyChat(){
        DynamicForm signupForm = new DynamicForm().bindFromRequest();
        String adminid = session().get("adminlog");
        String reply =signupForm.field("message").value();
        String chatid =signupForm.field("chatid").value();

        Timestamp replytime = new Timestamp(new Date().getTime());
        SqlUpdate update = Ebean. createSqlUpdate("update inquiry set admin_id=:admin_id, reply=:reply, reply_status=:reply_status,replied_at=:replied_at  WHERE id=:id")
                .setParameter("admin_id", adminid)
                .setParameter("reply", reply)
                .setParameter("reply_status", true)
                .setParameter("replied_at", replytime)
                .setParameter("id", chatid);
        int rowsCount = update.execute();

        flash("success", "Reply successfully sent");
        return ok(admin_agentinquiries.render("success", ""));
    }



}
