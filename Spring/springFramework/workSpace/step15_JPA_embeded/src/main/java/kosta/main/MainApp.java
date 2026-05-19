package kosta.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.entity.ContactInfo;
import kosta.entity.Member;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("-----JPA 시작-----");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transation = em.getTransaction();

		transation.begin();
		
		//  Member 등록
		/*
		em.persist(Member.builder()
				.name("도연")
				.contactInfo(ContactInfo.builder()
						.mobilePhone("010-8888-7777")
						.housePhone("031-444-5555")
					 	.companyPhone("031-111-2222")
						.build())
				.build());
		*/
		
		transation.commit();

		em.close();
		emf.close();

		System.out.println("-----JPA 끝-----");
	}
}