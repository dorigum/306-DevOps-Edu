package kosta.main;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.entity.Customer;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("-----JPA 시작-----");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transation = em.getTransaction();

		transation.begin();

		// 1. 등록
		// em.persist(Customer.builder().userName("도연").age(20).birthDay(newDate()).addr("경기").build());
		// em.persist(Customer.builder().userName("나연").build());
		// em.persist(Customer.builder().userName("순이2").build());
		
		/*
		Customer cu1 = Customer.builder().userName("미미2").age(24).birthDay(new Date()).addr("서울").build();
		Customer cu2 = Customer.builder().userName("나영2").age(21).birthDay(new Date()).addr("대전").build();
		Customer cu3 = Customer.builder().userName("효리2").age(27).birthDay(new Date()).addr("제주").build();

		em.persist(cu1);
		em.persist(cu2);
		em.persist(cu3);
		*/
		
		
		// -----------------------------------------------------------
		/* 
		// 2. 조회
		Customer cu = em.find(Customer.class, 2L);
		System.out.println("cu = " + cu);
		
		Customer cu2 = em.find(Customer.class, 2L);
		System.out.println("cu2 = " + cu2);
		
		Customer cu3 = em.find(Customer.class, 1L);
		System.out.println("cu3 = " + cu3);
		*/
		
		
		// -----------------------------------------------------------
		// 3. 수정
		/*
		Customer cu = em.find(Customer.class, 2L);
		System.out.println("cu = " + cu);
		cu.setAddr("대구3");
		cu.setAge(33);
		*/
		
		
		// -----------------------------------------------------------
		// 4. 삭제
		// Customer cu = em.find(Customer.class, 2L);
		// em.remove(cu);
		
		
		// -----------------------------------------------------------
		/* 다양한 조건의 쿼리를 작성하고 싶다.
		 * 즉, PK 대상이 아닌 일반 속성을 기준으로 조건을 설정하고 싶다.
		 * 
		 * 이런 경우에는 개발자가 직접 쿼리를 작성해야 한다.
		 * 단, DB 중심이 아닌 Entity 기반으로 Java 중심의 쿼리를 작성한다. -> JPQL 문법
		 * 개발자가 JPQL 문법으로 쿼리를 작성하면, JPA의 방언이 DB에 맞춰서 쿼리를 생성해준다.
		 */
		
		// 1. 나이가 21세 이상인 정보를 검색
		/*
		String sql = "select c from Customer c where c.age >= 21";
		List<Customer> list = em.createQuery(sql, Customer.class).getResultList();
		list.forEach(cu->System.out.println(cu));
		*/
		
		// 2. 인수 = parameter를 전달해서 조건으로 사용
		/*
		String sql = "select c from Customer c where c.age >= ? 1 or c.userName like ? 2";
		List<Customer> list = 
				em.createQuery(sql, Customer.class)
				.setParameter(1, 21)
				.setParameter(2, "%순이2%")
				.getResultList();
		list.forEach(cu->System.out.println(cu));
		*/
		
		// 3. parameter 순서가 아닌, 이름으로 바인딩하기
		/*
		String sql = "select c from Customer c where c.age >= :age or c.userName like :name";
		List<Customer> list = 
				em.createQuery(sql, Customer.class)
				.setParameter("age", 21)
				.setParameter("name", "%미미%")
				.getResultList();
		list.forEach(cu->System.out.println(cu));
		*/
		
		System.out.println("-----END 1-----");
		
		transation.commit();

		System.out.println("-----END 2-----");
		em.close();
		emf.close();

		System.out.println("-----JPA 끝-----");
	}
}