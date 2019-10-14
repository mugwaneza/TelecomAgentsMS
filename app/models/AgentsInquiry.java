package models;


import com.avaje.ebean.Ebean;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "inquiry")
public class AgentsInquiry  extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    public AgentsAccounts agents ;

    @ManyToOne(cascade = CascadeType.ALL)
    public AdminAccount admin ;

    @Column
    @Constraints.Required
    public String message;

    @Column
    @Constraints.Required
    public String reply;

   @Column
    @Constraints.Required
    public boolean reply_status;

    @Column
    @Constraints.Required
    public String replied_at;

    public Timestamp created_at = new Timestamp(new Date().getTime());



    public static Model.Finder<Long,AgentsInquiry> InquiryFinder = new  Model.Finder<>(Long.class,AgentsInquiry.class);


    // chats in agent dashboard
    public static List<AgentsInquiry> FindAgentChat(String agentid)
    {
        List<AgentsInquiry> agentinq;
        agentinq = AgentsInquiry.InquiryFinder.where().eq("agents_id",agentid).orderBy("id desc").findList();

        return agentinq;
    }


    // Detail chat of each message sender in admin
    public static List<AgentsInquiry> agentChat(String id)
    {
        List<AgentsInquiry> agentc ;
        agentc = InquiryFinder.where().eq("agents_id", id).findList();
        return  agentc;
    }

    public  static int Unread(){

        try {

         return  InquiryFinder.where("agents_id IS NOT NULL AND reply_status=false GROUP BY agents_id ").findRowCount();

        }
        catch (Exception  e) {
          System.out.println(e);
            }

      return 0;
    }




}
