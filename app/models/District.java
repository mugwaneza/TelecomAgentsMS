package models;

import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="district")
public class District extends Model {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    public Province province;

    @Column
    @Constraints.Required
    public String district;

    @Column
    @Constraints.Required
    public String description;

    public Timestamp Created_at = new Timestamp(new Date().getTime());

    public static Finder<Long,District> InfoDistricts = new Finder<>(Long.class,District.class);

    public static List<District> findDistrict() {
        return InfoDistricts.all();
    }

}
