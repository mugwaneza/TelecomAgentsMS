package controllers;

import models.*;
import org.mindrot.jbcrypt.BCrypt;
import play.data.DynamicForm;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import views.html.adminaccounts;
import views.html.signin_admin;
import views.html.signin_agent;
import views.html.signup_agent;

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

            //    create new session
            session("adminlog", id);

            return  redirect("/dashboard");
        }
        else {

            flash("error", "Invalid email or password ");
            return  ok(signin_admin.render("error"));
        }
    }





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




}
