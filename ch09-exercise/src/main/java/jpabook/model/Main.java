package jpabook.model;

import jpabook.model.embedded.Address;
import jpabook.model.entity.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;
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

    public static void logic(EntityManager em){
        Member member=new Member();
        member.setName("member1");

        //Embedded type 데이터 추가
        member.setHomeAddress(new Address("통영", "몽돌 해수욕장", "660-123"));

        //컬렉션 데이터 추가
        member.getFavoriteFoods().add("짬봉");
        member.getFavoriteFoods().add("짜장");
        member.getFavoriteFoods().add("탕수육");

        //Embedded Type컬렉션 데이터 추가
        member.getAddressHistory().add(new Address("서울","강남","123-123"));
        member.getAddressHistory().add(new Address("서울","강북","000-000"));
        member.getAddressHistory().add(new Address("서울","송파구","123-123"));

        em.persist(member);
        em.flush();
        em.clear();

        //컬렉션 데이터 수정과정
        Member newMember=em.find(Member.class,1L);
        List<Address> addressList=newMember.getAddressHistory();
        addressList.remove(new Address("서울","강남","123-123"));

        em.persist(newMember);
        em.flush();
    }

}
