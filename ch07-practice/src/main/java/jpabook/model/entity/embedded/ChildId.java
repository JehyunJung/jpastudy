package jpabook.model.entity.embedded;

import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@EqualsAndHashCode
@Embeddable
public class ChildId implements Serializable {
    private String parentId;

    @Column(name="CHILD_ID")
    private String id;
}
