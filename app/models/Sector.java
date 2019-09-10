package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.libs.Json;
import play.mvc.Result;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="sector")
public class Sector extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    public District district ;

    @Column
    @Constraints.Required
    public String sector;

    @Column
    @Constraints.Required
    public String description;

    public Timestamp Created_at = new Timestamp(new Date().getTime());

    public static Finder<Long,Sector> InfoSectors = new Finder<>(Long.class,Sector.class);

    public static List<Sector> findSectors() {
        return InfoSectors.all();
    }
      // get the list of sector by id of selected district
    public static List<Sector> ResultSectors(Long id) {
        return InfoSectors.where().eq("district_id", id).findList();
    }

    public static Sector isSectorExist(String sector) {
        return  InfoSectors.where()
                .eq("sector",sector).findUnique();
    }
}
