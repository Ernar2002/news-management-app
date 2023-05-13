package kz.strongteam.strongteamnews.models;

import kz.strongteam.strongteamnews.models.enums.EActive;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@MappedSuperclass
@Data
public class BaseModel {
    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @CreatedDate
    private Date createdAt = new Date();

    private Date updatedAt = new Date();

    @Enumerated(EnumType.STRING)
    @Column(name = "active")
    private EActive active = EActive.ACTIVE;
}
