package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
public class Topic extends BaseModel {

    private String name;
}
