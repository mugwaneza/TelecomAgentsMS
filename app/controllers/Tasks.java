package controllers;

import models.AgentApplication;

import java.util.List;

public class Tasks extends Application {



    public static List<AgentApplication> Tasks(){

        String ExistingSession = session().get("agentlog");

        List<AgentApplication> Isapplicant  =AgentApplication.applicationsList(ExistingSession);

        return Isapplicant;
    }

}
