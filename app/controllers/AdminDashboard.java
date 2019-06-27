package controllers;

import models.*;
import play.data.DynamicForm;
import play.mvc.Controller;
import play.mvc.Result;

public class AdminDashboard extends Controller {

    public static Result DashboardIndex() {

        return ok(views.html.admin.render());

    }

       public static Result DashboardProvince() {

        return ok(views.html.adminprovince.render());

    }

     public static Result DashboardDistrict() {

        return ok(views.html.admindistrict.render());

    }

    public static Result DashboardSector() {

        return ok(views.html.adminsector.render());

    }

      public static Result DashboardCell() {

        return ok(views.html.admincell.render());

    }

    public static Result DashboardAdminAccounts() {

        return ok(views.html.adminaccounts.render());

    }
      // Bind form data
     public static  DynamicForm  myform;

    public static Result DashboardCreateProvince(){
        myform = DynamicForm.form().bindFromRequest();
        Province prov = new Province();
        prov.province = myform.field("province").value();
        prov.description = myform.field("description").value();
        prov.save();
            return ok();
    }

    public static Result DashboardCreateDistrict(){
        myform = DynamicForm.form().bindFromRequest();

        District district = new District();

        String provinceid = myform.field("province").value();
        district.province = Province.InfoProvinces.byId(Long.parseLong(provinceid));
        district.district = myform.field("district").value();
        district.description = myform.field("description").value();
        district.save();
        return ok("success");
    }


    public static Result DashboardCreateSector(){
        myform = DynamicForm.form().bindFromRequest();


        Sector sector = new Sector();
        String districtid = myform.field("district").value();
        sector.district = District.InfoDistricts.byId(Long.parseLong(districtid));
        sector.sector = myform.field("sector").value();
        sector.description = myform.field("description").value();
        sector.save();
        return ok("success");
    }


    public static Result DashboardCreateCell(){

        myform = DynamicForm.form().bindFromRequest();

        Cell cell = new Cell();
        String sectorid = myform.field("sector").value();
        cell.sector = Sector.InfoSectors.byId(Long.parseLong(sectorid));
        cell.cell = myform.field("cell").value();
        cell.description = myform.field("description").value();
        if(myform.hasErrors()) {
            return badRequest(views.html.admincell.render());

        }

        cell.save();
        return ok("success");
    }






}
