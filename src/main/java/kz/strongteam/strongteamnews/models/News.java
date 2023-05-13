package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
public class News extends BaseModel {

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    @ManyToOne
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
}
