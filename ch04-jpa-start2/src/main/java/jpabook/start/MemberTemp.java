package jpabook.start;

import javax.persistence.*;

@Entity
@Table(name="MEMBER_TEMP")

public class MemberTemp {
    @Id
    private int id;
    @Transient
    private String firstName;

    @Transient
    private String lastName;

//    private String fullName;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Access(AccessType.PROPERTY)
    public String getFullName(){
        return firstName+lastName;
    }

}
