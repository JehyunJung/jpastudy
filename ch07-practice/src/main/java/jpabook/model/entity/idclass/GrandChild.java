package jpabook.model.entity.idclass;

import lombok.Data;

import javax.persistence.*;

@IdClass(GrandChildId.class)
@Entity
@Data
public class GrandChild {
    @Id
    @JoinColumns({
            @JoinColumn(name = "PARENT_ID"),
            @JoinColumn(name = "CHILD_ID")
    })
    private Child child;

    @Id
    @Column(name = "GRANDCHILD_ID")
    private String id;

    String name;
}
