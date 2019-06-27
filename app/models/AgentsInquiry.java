package models;


import play.data.validation.Constraints;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "inquiry")
public class AgentsInquiry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false)
    public Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    public ApprovedAgents agent ;

    @ManyToOne(cascade = CascadeType.ALL)
    public AdminAccount admin ;



    @Column
    @Constraints.Required
    public String message;

    @Column
    @Constraints.Required
    public String reply;

   @Column
    @Constraints.Required
    public String reply_status;

    @Column
    @Constraints.Required
    public String replied_at;

    public Timestamp approved_at = new Timestamp(new Date().getTime());

}
