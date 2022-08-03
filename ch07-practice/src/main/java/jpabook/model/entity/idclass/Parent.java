package jpabook.model.entity.idclass;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Parent {
    @Id
    @Column(name = "PARENT_ID")
    private String id;

    private String name;
}
