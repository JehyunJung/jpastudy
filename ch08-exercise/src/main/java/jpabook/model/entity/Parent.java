package jpabook.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="PARENT")
public class Parent {
    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @OneToMany(mappedBy="parent",cascade=CascadeType.ALL,orphanRemoval = true)
    private List<Child> childs=new ArrayList<Child>();

    public Parent(String name) {
        this.name=name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Child> getChilds() {
        return childs;
    }

    public void setChilds(List<Child> childs) {
        this.childs = childs;
    }
}
