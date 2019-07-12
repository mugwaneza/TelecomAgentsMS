package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "admin")
public class AdminAccount extends Model {

       @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

//    @ManyToMany(cascade = CascadeType.ALL)
//    public List<AgentApplication> agent;


    @Column
    @Constraints.Required
    public String fullname;

    @Column
    @Constraints.Required
    public String gender;

    @Column
    @Constraints.Required
    public String company;

    @Column
    @Constraints.Required
    public String email;

    @Column
    @Constraints.Required
    public String password;
    @Column
    @Constraints.Required
    public String address;

    public Timestamp Created_at = new Timestamp(new Date().getTime());

    public static Finder<Long,AdminAccount> FindAdmin = new Finder < >(Long.class, AdminAccount.class);


    public static  AdminAccount isEmailExist(String email){

        return FindAdmin.where().eq("email", email).findUnique();
    }
}
