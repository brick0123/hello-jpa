package hellojpa;

import hellojpa.mapping.Member;
import org.hibernate.Hibernate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        // EntityManagerFactory는 애플리케이션 로딩 시점에 한 개만 만들어놔야함.

        EntityManager em = emf.createEntityManager();
        // 트랜잭션 처리를 하는 행위를 할 때마다 EntityManager 만들어줘야 함. (고객의 요청이 올 때 마다, 쓰레드마다 공유 XX)

        EntityTransaction tx = em.getTransaction();
        tx.begin(); // 트랜잭션 시작
        try {
//            Member member = new Member();
//            member.setId(2L);
//            member.setName("HelloB");
//            em.persist(member); // 삽입

//            Member findMember = em.find(Member.class, 1L); // 찾기
//            findMember.setName("HelloJPA"); 수정

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .getResultList();
//            // JPA에서는 테이블을 대상으로 쿼리를 짜지 않는다.  Member객체를 대상으로 쿼리
//            for (Member member : result) {
//                System.out.println("member.getName() = " + member.getName());
//            }

//            List<Member> result = em.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(5)
//                    .setMaxResults(10)
//                    .getResultList();  // 페이지네이션도 가능
//            Member member1 = new Member(10L, "spring");
//            Member member2 = new Member(11L, "jpa");`
//
//            em.persist(member1);
//            em.persist(member2);
//            System.out.println("========== AFTER ============");

//            Movie movie = new Movie();
//            movie.setDirector("aaa");
//            movie.setActor("bbb");
//            movie.setName("바람과 함께 사라지다");
//            movie.setPrice(10000);
//
//            em.persist(movie);
//
//            em.flush();
//            em.clear();
//
//            em.find(Movie.class, movie.getId());

//            Member member = new Member();
//            member.setUsername("user1");
//            member.setCreatedBy("kim");
//            member.setCreatedDate(LocalDateTime.now());
//
//            em.persist(member);

            Member member = new Member();
            member.setUsername("hello");

            em.persist(member);

            em.flush();
            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
            Member findMember = em.getReference(Member.class, member.getId()); // 쿼리가 안 나감

//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());
            // 실제로 사용하면 쿼리가 나감.
            // Id는 인자로 넣은거기 때문에 쿼리가 안 나가는데 username은 나감

            System.out.println("findMember = " + findMember.getClass()); // Proxy
            Hibernate.initialize(findMember); // 강제 초기화

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }



    }
}
