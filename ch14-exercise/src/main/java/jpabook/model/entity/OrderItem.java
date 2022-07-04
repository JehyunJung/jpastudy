package jpabook.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="ORDER_ID")
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ITEM_ID")
    private Item item; // 주문 상품

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;

        if(!order.getOrderItems().contains(this))
            order.getOrderItems().add(this);
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;

        if(item.getOrderItems().contains(this))
            item.getOrderItems().add(this);
    }
}