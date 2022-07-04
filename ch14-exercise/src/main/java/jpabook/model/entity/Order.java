package jpabook.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NamedEntityGraph(
        name = "Order.withAll",
        attributeNodes = {
                @NamedAttributeNode("member"),
                @NamedAttributeNode(value = "orderItems", subgraph = "orderItems")
        },
        subgraphs = @NamedSubgraph(name = "orderItems", attributeNodes = {
                @NamedAttributeNode("item")
        })
)
@Entity
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)//필수 관계 설정
    @JoinColumn(name = "MEMBER_ID")
    private Member member; // 주문 회원

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

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

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;

        if(!member.getOrders().contains(this))
            member.getOrders().add(this);
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public void addOrderItem(OrderItem orderItem){
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }
}