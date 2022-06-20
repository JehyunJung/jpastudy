package jpabook.persistence;

import javax.persistence.*;
import jpabook.persistence.Member;
import java.util.List;

/**
 * @author holyeye
 */
public class JpaMain {

    public static void main(String[] args) {

        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");

        Member member=createMember(emf,"memberA","회원1",10);

        //준영속 상태인 엔티티에 변경을 수행해도 DB에 적용되지 않는다.
        member.setUsername("회원");

        //DB에 있는 엔티티를 확인해서 준영속 상태인 엔티티에 대한 변경이 적용되지 않았음을 확인하자
        Member newMember=findMember(emf,member.getId());
        System.out.println("newMember username = " + newMember.getUsername());


        mergeMember(emf,member);
    }

    static Member createMember(EntityManagerFactory emf,String id, String username,int age) {
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        Member member = new Member();

        try {
            tx.begin();
            //비즈니스 로직 수행
            member.setId(id);
            member.setUsername(username);
            member.setAge(age);

            //멤버를 영속성 컨텍스트에 저장
            em.persist(member);
            tx.commit();
        }
        catch(Exception e) {
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            //멤버는 준영속 상태가 된다.
            em.close();
        }
        return member;

    }

    static Member findMember(EntityManagerFactory emf,String id){
        EntityManager em=emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();
        Member member=null;
        try{
            tx.begin();

            member=em.find(Member.class,id);

            tx.commit();
        }catch(Exception e){
            e.printStackTrace();
            tx.rollback();
        }
        finally{
            em.close();
        }
        return member;
    }

    static void mergeMember(EntityManagerFactory emf,Member member){
        EntityManager em= emf.createEntityManager();
        EntityTransaction tx=em.getTransaction();

        try {
            tx.begin();

            //비즈니스 로직 수행
            //병합을 통해 해당 준영속 엔티티를 영속 상태로 다시 만든다.
            Member mergedMember = em.merge(member);
            tx.commit();

            //준영속 상태의 엔티티
            System.out.println("member username:"+ member.getUsername());

            //영속 상태의 엔티티
            System.out.println("mergeMember username:"+mergedMember.getUsername());
        }catch(Exception e){
            tx.rollback();
        }
        finally{
            em.close();
        }
    }

}
