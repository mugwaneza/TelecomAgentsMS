package models;


import play.data.validation.Constraints;
import play.db.ebean.Model;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Entity
@Table(name ="province")
public class Province extends Model {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;

    @Column
    @Constraints.Required
    public String province;

    @Column
    @Constraints.Required
    public String description;

    public Timestamp Created_at = new Timestamp(new Date().getTime());


    public static Finder<Long,Province> InfoProvinces = new Finder<>(Long.class,Province.class);

    public static List<Province> findProvince() {
        return InfoProvinces.all();
    }


       public static Province isProvinceExist(String province) {
        return  InfoProvinces.where()
                .eq("province",province).findUnique();
    }
}