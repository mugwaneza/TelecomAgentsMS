package models;

import com.avaje.ebean.ExpressionList;
import controllers.AdminDashboard;
import play.data.DynamicForm;
import play.data.validation.Constraints;
import play.db.ebean.Model;
import play.mvc.Results;

import javax.persistence.*;
import javax.xml.transform.Result;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

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

    public static Cell isCellExist(String cell) {
        return  InfoCell.where()
                .eq("cell",cell).findUnique();
    }

    public  static List<Cell> findCells(){
    return InfoCell.all();
}

    // get the list of cell by id of selected district
    public static List<Cell> ResultCells(Long id) {
        return InfoCell.where().eq("sector_id", id).findList();
    }


}
