package models;

import com.avaje.ebean.Expr;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import sun.management.Agent;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "applicant")
public class AgentApplication extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @OneToOne(cascade = CascadeType.ALL)
    public AgentsAccounts agent ;

    @ManyToOne(cascade = CascadeType.ALL)
    public District district ;

    @ManyToOne(cascade = CascadeType.ALL)
    public Sector sector ;

    @ManyToOne(cascade = CascadeType.ALL)
    public Cell cell ;

//    @ManyToMany(mappedBy="agent", cascade = CascadeType.ALL)
//    public List<AdminAccount> administrator;

    @Column
    @Constraints.Required
    public String agromeration;

    @Column
    @Constraints.Required
    public String firstname;

    @Column
    @Constraints.Required
    public String lastname;

    @Column
    @Constraints.Required
    public String phonenumber;

    @Column
    @Constraints.Required
    public String address;

    @Column
    @Constraints.Required
    public String nationality;

    @Column
    @Constraints.Required
    public String nid;

    @Column
    @Constraints.Required
    public String company;

    @Column
    @Constraints.Required
    public String sitename;

    @Column
    @Constraints.Required
    public String reject_status;

    @Column
    @Constraints.Required
    public String passportphoto;

    public Timestamp Created_at = new Timestamp(new Date().getTime());


//         public static Look<Cell> on = new Look<>(Cell.class);
//
//       @Exist
//     public boolean exist() {
//         return on.exist("cell", this.cell);
//     }
    public static   Finder<Long, AgentApplication> findApplication = new Finder<>(Long.class,AgentApplication.class);


    public  static  List<AgentApplication> applicationsList (String session){
        return findApplication.where().eq("agent_id", session).findList();
    }

    public  static  AgentApplication application (String session){
        return findApplication.where().eq("agent_id", session).findUnique();
    }




}
