package framework.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Topics {
    @Id
    private int id;
    private String name;
    private String date;
}
