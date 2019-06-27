package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

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

}
