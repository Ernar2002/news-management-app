package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "sources")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Source extends BaseModel {

    private String name;

    private String url;
}
