package jpabook.model;

import jpabook.model.entity.Child;
import jpabook.model.entity.Parent;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

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

    public static void logic(EntityManager em){
        /*Parent parent=em.find(Parent.class,1L);

        Child child1=new Child("child1");
        child1.setParent(parent);
        parent.getChilds().add(child1);
        em.persist(child1);*/
        Child newChild=em.find(Child.class,1L);
        Parent newParent=newChild.getParent();
        System.out.println("newChild.getName() = " + newChild.getName());
        System.out.println("newParent.getClass() = " + newParent.getClass());
        



        /*Child child1=new Child("child1");
        child1.setParent(parent);
        parent.getChilds().add(child1);

        Child child2=new Child("child2");
        child2.setParent(parent);
        parent.getChilds().add(child2);

        em.persist(parent);
        
        parent=em.find(Parent.class,1L);
        List<Child> childs=parent.getChilds();

        System.out.println("parent.getName() = " + parent.getName());
        for(Child child: childs){
            System.out.println("child.getParent() = " + child.getParent());
            System.out.println("child.getId() = " + child.getId());
            System.out.println("child.getName() = " + child.getName());
        }

        childs.remove(0);
        em.flush();

        Child newChild=em.find(Child.class,2L);
        System.out.println("newChild.getParent() = " + newChild.getId());
        System.out.println("newChild.getParent() = " + newChild.getParent());
        System.out.println("newChild.getName() = " + newChild.getName());*/
    }

}
