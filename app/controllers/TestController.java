package controllers;

import models.Sector;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;

public class TestController extends Controller {

    public static Result test(Long id){

        List<Sector> mysector = Sector.findS(id);
//        System.out.println(mysector);
        String ab =mysector.toString();
        return ok("" + ab);
      }


}
