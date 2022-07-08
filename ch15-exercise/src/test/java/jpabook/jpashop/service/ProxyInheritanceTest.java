package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:appConfig.xml")

public class ProxyInheritanceTest {
    @PersistenceContext
    EntityManager em;

    @Test
    public void 상속관계프록시() throws Exception {

        //테스트 데이터 준비
        Book saveBook = new Book();
        saveBook.setName("jpabook");
        saveBook.setAuthor("kim");
        em.persist(saveBook);

        em.flush();
        em.clear();

        //테스트 시작
        Item proxyItem = em.getReference(Item.class, saveBook.getId());
        System.out.println("proxyItem = " + proxyItem.getClass());

        if(proxyItem instanceof Book) {
            System.out.println("proxyItem instanceof Book");
            Book book = (Book) proxyItem;
            System.out.println("책 저자 = " + book.getAuthor());
        }

        //결과 검증
        Assert.assertFalse(proxyItem.getClass() == Book.class);
        Assert.assertFalse(proxyItem instanceof Book);
        Assert.assertTrue(proxyItem instanceof Item);
    }


}