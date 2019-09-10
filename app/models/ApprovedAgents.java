package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "approved_agents")
public class ApprovedAgents extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    public AgentApplication applicant;

    @ManyToOne(cascade = CascadeType.ALL)
    public AdminAccount admin ;


    @Column
    @Constraints.Required
    public Boolean status;

    @Column
    @Constraints.Required
    public String walletnumber;

    @Column
    @Constraints.Required
    public String airtimenumber;

    public Timestamp approved_at = new Timestamp(new Date().getTime());

    public static Finder<Long,ApprovedAgents> approved = new Finder<>(Long.class,ApprovedAgents.class);

    public  static  List<ApprovedAgents> approved(){

        return approved.where().eq("status", 1).findList();
    }



}
