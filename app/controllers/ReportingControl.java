package controllers;

import com.avaje.ebean.Ebean;
import com.avaje.ebean.SqlUpdate;
import models.AgentApplication;
import models.ApprovedAgents;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.db.ebean.Model;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import scalax.file.Path;
import java.nio.file.Paths;

import java.io.File;
import java.util.*;

import static play.data.Form.form;

public class ReportingControl extends Controller {

    public  static  DynamicForm myform;


    public static Result ListAppicants(){


        return ok(views.html.admin_agentsapproval.render(""));
    }
    public static List<ApprovedAgents> searched ;
    public static Result ApplicationDecisonSearch(String mobile){

         searched = AgentApplication.Searched(mobile);

        return ok(views.html.admin_applicantsdecision.render("", searched));
    }

    public static Result ApplicationDecison(){
         List<ApprovedAgents> empty = null;
        return ok(views.html.admin_applicantsdecision.render("" ,empty));
    }

    public static Result UpdateAgent(){
        myform  = form().bindFromRequest();
        String adminid = myform.field("id2").value();

        String walletno = myform.field("walletnumber2").value();
        String airtimeno = myform.field("airtimenumber2").value();
        String inumber = myform.field("address2").value();


        String district = myform.field("distri2").value();
        String sector = myform.field("sector2").value();
        String cell = myform.field("cell2").value();
        String idnumber = myform.field("idnumber2").value();
        String village = myform.field("agglomeration2").value();


        Http.MultipartFormData formFile = request().body().asMultipartFormData();
        Http.MultipartFormData.FilePart uploadedFile = formFile.getFile("passport2");

        // get file name from uploaded
        String fileName = uploadedFile.getFilename();
        File SourceUploadedfile = uploadedFile.getFile();

        //Rename a file
        String photoname = (new Date()).getTime() +"_"+ fileName;
        File DestinationFilespath = new File("public/uploads/"+ photoname);

        String passport2 = new File("/uploads/"+ photoname).getPath();
        String nationality = myform.field("nationality2").value();
        String nid = myform.field("nationalid2").value();

        String firstname = myform.field("firstname2").value();
        String lastname = myform.field("lastname2").value();
        String phoneno = myform.field("phonenumber2").value();
        String company = myform.field("company2").value();
        String site = myform.field("sitename2").value();
        String address = myform.field("address2").value();

        SqlUpdate update = Ebean. createSqlUpdate("UPDATE applicant SET firstname=:firstname, lastname=:lastname, phonenumber=:phonenumber, company=:company, sitename=:sitename, address=:address, district_id=:district_id,sector_id=:sector_id,cell_id=:cell_id, agromeration=:agromeration,nationality=:nationality, passportphoto=:passportphoto, nid=:nid WHERE id=:id")
                .setParameter("firstname", firstname)
                .setParameter("lastname", lastname)
                .setParameter("phonenumber", phoneno)
                .setParameter("company", company)
                .setParameter("sitename", site)
                .setParameter("address", address)
                .setParameter("district_id", district)
                .setParameter("sector_id", sector)
                .setParameter("cell_id", cell)
                .setParameter("agromeration", village)
                .setParameter("nationality", nationality)
                .setParameter("passportphoto", passport2)
                .setParameter("nid", nid)
                .setParameter("id", adminid);
         int rows = update.execute();
         if (rows > 0){
             SourceUploadedfile.renameTo(DestinationFilespath); //here you are moving photo to path directory

             SqlUpdate update2 = Ebean. createSqlUpdate("UPDATE approved_agents SET walletnumber=:walletnumber, airtimenumber=:airtimenumber WHERE id=:id")
                 .setParameter("walletnumber", walletno)
                 .setParameter("airtimenumber", airtimeno)
                 .setParameter("id", idnumber);
             int row2 = update2.execute();
         }

        return redirect("/manage/decision");
    }


    public static Result ShowAdmins(){
        return ok(views.html.admin_manamentofaccounts.render(""));
    }

    // delete  admin row using variables capture from table with javascript file called all_agents_style

    public static Result DeleteAdmins(){

        myform  = form().bindFromRequest();
        String adminid = myform.field("deletid").value();

        SqlUpdate update = Ebean. createSqlUpdate("delete from admin  WHERE id=:id")
                .setParameter("id", adminid);
        int rowsCount = update.execute();

        flash("error", "Admin account successfully deleted");

        return ok(views.html.admin_manamentofaccounts.render("error"));
    }

    // update admin row using variables capture from table with javascript file called all_agents_style
    public static Result UpdateAdmins(){

        myform  = form().bindFromRequest();
        String adminid = myform.field("userid").value();
        String fullname = myform.field("fullname").value();
        String gender = myform.field("gender").value();
        String company = myform.field("company").value();
        String email = myform.field("email").value();
        String Texpassword = myform.field("password").value();
        String passsword =  BCrypt.hashpw(Texpassword, BCrypt.gensalt());
        String address = myform.field("address").value();

        SqlUpdate update = Ebean. createSqlUpdate("UPDATE admin SET fullname=:fullname, gender=:gender, company=:company, email=:email, password=:password, address=:address WHERE id=:id")
                .setParameter("fullname", fullname)
                .setParameter("gender", gender)
                .setParameter("company", company)
                .setParameter("email", email)
                .setParameter("password", Texpassword)
                .setParameter("address", address)
                .setParameter("id", adminid);
        int rows = update.execute();

        flash("success", "Admin account successfully updated");

        return ok(views.html.admin_manamentofaccounts.render("success"));
    }

    public static Result SendMessage(){
        return ok();
    }



}
