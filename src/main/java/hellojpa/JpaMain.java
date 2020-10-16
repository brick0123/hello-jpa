package hellojpa;

import hellojpa.cascade.Child;
import hellojpa.cascade.Parent;
import hellojpa.mapping.Address;
import hellojpa.mapping.Member;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

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

            // =========== 지연로딩 ================/
//            Team team = new Team();
//            team.setName("teamA");
//            em.persist(team);
//
//            Member member = new Member();
//            member.setUsername("hello");
//            member.setTeam(team);
//            em.persist(member);
//
//            em.flush();
//            em.clear();

//            Member findMember = em.find(Member.class, member.getId());
//            Member findMember = em.getReference(Member.class, member.getId()); // 쿼리가 안 나감

//            System.out.println("findMember = " + findMember.getId());
//            System.out.println("findMember = " + findMember.getUsername());
            // 실제로 사용하면 쿼리가 나감.
            // Id는 인자로 넣은거기 때문에 쿼리가 안 나가는데 username은 나감

//            System.out.println("findMember = " + findMember.getClass()); // Proxy
//            Hibernate.initialize(findMember); // 강제 초기화

//            List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();

            // =========== 영속성 전이와 고아 객체 ================/

//            Child child1 = new Child();
//            Child child2 = new Child();
//
//            Parent parent = new Parent();
//            parent.addChild(child1);
//            parent.addChild(child2);
//
//            em.persist(parent);
//
//            em.flush();
//            em.clear();
//
//            Parent findParent = em.find(Parent.class, parent.getId());
//            findParent.getChildList().remove(0);

//            em.remove(parent); // 부모 자식 모두 삭제

            // == 값 타입 == //\
            Address homeAddress = new Address("city", "street", "zipcode");

            Member member = new Member();
            member.setUsername("member1");
            member.setAddress(homeAddress);
            em.persist(member);


            Member member2 = new Member();
            member2.setUsername("member2");
            member2.setAddress(homeAddress);
            em.persist(member2);

            tx.commit();
        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback();
        } finally {
            em.close();
            emf.close();
        }



    }
}
