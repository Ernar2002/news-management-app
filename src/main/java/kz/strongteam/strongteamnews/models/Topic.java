package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "topics")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Topic extends BaseModel {

    private String name;
}
