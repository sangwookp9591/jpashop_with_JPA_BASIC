package jpabook.jpashop;

import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx =em.getTransaction();

        tx.begin();
        try{
            Order order = new Order();
     //       order.addOrderItem(new OrderItem());
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);

            em.persist(orderItem);
        }catch (Exception e){
            tx.rollback();
        }finally {
            em.close();
        }
        emf.close();
        //양뱡향 연관관계를 하는 이유는 개발상의 편의 때문이다/.
        //order entity를 조회했을때 orderItem을 알고 싶어할때
        //실전은 jpql을 많이 작성하는데 oirder를 조회할떄 orderItems를 뽑고 싶어한다.
        //jpql을 실무에서 복잡하게 짜기땨문에 양방향으로 만들어서 쓴다.
        //하다보면 양방향이아닌데 양뱡향이 필요할경우 그냥 단방향에서 조회에서 쓰면된다.
        //핵심은 단방향을 잘설계하는게 중요하다.

    }
}
