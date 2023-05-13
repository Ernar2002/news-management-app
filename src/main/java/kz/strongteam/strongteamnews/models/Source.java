package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sources")
@AllArgsConstructor
@NoArgsConstructor
public class Source extends BaseModel {

    private String name;

    private String url;
}
