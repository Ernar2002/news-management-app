package kz.strongteam.strongteamnews.models;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "news")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class News extends BaseModel {

    private String title;

    private String content;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "source_id", nullable = false)
    private Source source;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "topic_id", nullable = false)
    private Topic topic;
}
