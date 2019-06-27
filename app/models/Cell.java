package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name ="cell")
public class Cell extends Model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    public Sector sector ;

    @Column
    @Constraints.Required
    public String cell;

    @Column
    @Constraints.Required
    public String description;

    public Timestamp Created_at = new Timestamp(new Date().getTime());



      public static   Finder<Long,Cell> InfoCell = new Finder<>(Long.class,Cell.class);






}
