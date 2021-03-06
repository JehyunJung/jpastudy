package jpabook.model.entity.embedded;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class GrandChild {
    @EmbeddedId
    private GrandChildId id;

    @MapsId("childId")
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;

    String name;
}
