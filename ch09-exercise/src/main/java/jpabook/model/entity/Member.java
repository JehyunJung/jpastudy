package jpabook.model.entity;


import jpabook.model.embedded.Address;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
public class Member{
    @Id
    @GeneratedValue
    private Long id;
    private String name;

    //근무 기간
    @Embedded
    Address homeAddress;

    @ElementCollection
    @CollectionTable(name="FAVORITE_FOOD",joinColumns= @JoinColumn(name="MEMBER_ID"))
    @Column(name="FOOD_NAME")
    private Set<String> favoriteFoods=new HashSet<String>();

    @ElementCollection
    @CollectionTable(name="ADDRESS",joinColumns= @JoinColumn(name="MEMBER_ID"))
    private List<Address> addressHistory=new ArrayList<Address>();

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

    public Address getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(Address homeAddress) {
        this.homeAddress = homeAddress;
    }

    public Set<String> getFavoriteFoods() {
        return favoriteFoods;
    }

    public void setFavoriteFoods(Set<String> favoriteFoods) {
        this.favoriteFoods = favoriteFoods;
    }

    public List<Address> getAddressHistory() {
        return addressHistory;
    }

    public void setAddressHistory(List<Address> addressHistory) {
        this.addressHistory = addressHistory;
    }
}