package jpabook.model;

import jpabook.model.embedded.Address;
import jpabook.model.entity.Item;
import jpabook.model.entity.Member;
import jpabook.model.entity.Order;
import jpabook.model.entity.OrderItem;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by 1001218 on 15. 4. 5..
 */
public class Main {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성

        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {

            tx.begin(); //트랜잭션 시작
            logic(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }
    public static void insert(EntityManager em){
        Member member1 = new Member();
        member1.setName("member1");
        em.persist(member1);

        Order order1 = new Order();
        order1.setName("order1");
        order1.setMember(member1);

        Order order2 = new Order();
        order2.setName("order2");
        order2.setMember(member1);

        Item item1=new Item();
        item1.setName("item1");
        em.persist(item1);

        OrderItem oi1=new OrderItem();
        oi1.setItem(item1);
        order1.addOrderItem(oi1);

        Item item2=new Item();
        item2.setName("item2");
        em.persist(item2);

        OrderItem oi2=new OrderItem();
        oi2.setItem(item2);
        order1.addOrderItem(oi2);
    }
    public static void logic(EntityManager em){
     /*   EntityGraph graph = em.getEntityGraph("Order.withAll");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);

        Order order = em.find(Order.class, 1L, hints);
        /*List<Order> resultList = em.createQuery("SELECT o FROM Order o JOIN FETCH o.member WHERE o.id = :orderId", Order.class)
                .setParameter("orderId", 1L)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("Order.withAll"))
                .getResultList();*/


/*      Member member = em.find(Member.class, 1L);
        List<Order> orders = member.getOrders();
        for (Order order : orders) {
            System.out.println("order.getName() = " + order.getName());
        }*/

      //insert(em);


      /*  List<Order> orders = em.createQuery("SELECT o FROM Order o join fetch o.member", Order.class)
                .setHint("javax.persistence.fetchgraph", em.getEntityGraph("Order.withAll"))
                .getResultList();
        for(Order order:orders){
            System.out.println("order.getName() = " + order.getName());
            System.out.println("order.getMember() = " + order.getMember());
            System.out.println("order.getMember().getName() = " + order.getMember().getClass());

            System.out.println("order.getOrderItems() = " + order.getOrderItems());
            List<OrderItem> orderItems = order.getOrderItems();
            for (OrderItem orderItem : orderItems) {
                System.out.println("orderItem.getItem() = " + orderItem.getItem());
                System.out.println("orderItem.getItem() = " + orderItem.getItem().getName());
            }
        }*/

        EntityGraph graph = em.createEntityGraph(Order.class);
        graph.addAttributeNodes("member");
        Subgraph<OrderItem> orderItems=graph.addSubgraph("orderItems");
        orderItems.addAttributeNodes("item");

        Map hints = new HashMap();
        hints.put("javax.persistence.fetchgraph", graph);

        Order order = em.find(Order.class,11L, hints);

    }

}
