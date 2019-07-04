package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "agents")
public class AgentsAccounts extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @Column
    @Constraints.Required
    public String fullname;

    @Column
    @Constraints.Required
    public String gender;

    @Column
    @Constraints.Required
    public String email;

    @Column
    @Constraints.Required
    public String password;

    public Timestamp Created_at = new Timestamp(new Date().getTime());

   public static Finder<Long,AgentsAccounts> FindAgent = new Finder<Long, AgentsAccounts>(Long.class, AgentsAccounts.class);

    public static  AgentsAccounts isEmailExist(String email){

        return FindAgent.where().eq("email", email).findUnique();
    }

    public static AgentsAccounts authenticate(String email, String password) {
        return FindAgent.where()
                .eq("email", email)
                .eq("password", password)
                .findUnique();

    }


//    public  static boolean sd(){
//
//        List<AgentsAccounts> tasks = (List<AgentsAccounts>) FindAgent.where()
//                .ilike("name", "%coco%")
//                .orderBy("dueDate asc");
//
//         if (tasks.isEmpty()){
//             return false;
//          }else{
//             return true;
//         }
//
//    }
}
