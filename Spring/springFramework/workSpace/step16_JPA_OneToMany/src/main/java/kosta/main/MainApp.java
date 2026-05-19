package kosta.main;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import kosta.entity.Member;
import kosta.entity.Team;

public class MainApp {

	public static void main(String[] args) {
		System.out.println("-----JPA 시작-----");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAProject");
		EntityManager em = emf.createEntityManager();
		EntityTransaction transation = em.getTransaction();

		transation.begin();

		// Team 등록
		/*
		Team t1 = Team.builder().teamName("team01").build();
		Team t2 = Team.builder().teamName("team02").build();
		Team t3 = Team.builder().teamName("team03").build();

		em.persist(t1);
		em.persist(t2);
		em.persist(t3);

		// Member 등록
		em.persist(Member.builder().name("도연").age(20).team(t1).build());
		em.persist(Member.builder().name("나연").age(22).team(t1).build());
		em.persist(Member.builder().name("순이").age(21).team(t1).build());

		em.persist(Member.builder().name("철웅").age(30).team(t2).build());
		em.persist(Member.builder().name("반달").age(25).team(t2).build());
		*/
		
		// ----------------------------------------------------
		// 1. 회원 검색
		/*
		Member member = em.find(Member.class, 4L);
		System.out.println("member = " + member); // member.toString()
		
		System.out.println("회원의 팀 정보 검색하기!");
		Team team = member.getTeam();
//		System.out.println(team.getClass()); // class kosta.entity.Team$HibernateProxy$IBAs6MHo
		System.out.println("team = " + team);
		*/
		
		
		// ----------------------------------------------------
		// 2. 팀 검색 + 회원
		/*
		Team team = em.find(Team.class, 1L);
		System.out.println("team = " + team);
		*/
		
		
		// ----------------------------------------------------
		// 3. 삭제(Member(자식)를 삭제)
		/*
		Member member = em.find(Member.class, 4L);
		em.remove(member);
		*/
		
		
		// ----------------------------------------------------
		// 4. 삭제(Team(부모)을 삭제)
		/*
		Team team = em.find(Team.class, 3L); // 3L을 참조하고 있는 자식이 없는 경우
		em.remove(team);
		*/
		
		Team team = em.find(Team.class, 1L); // 1L을 참조하고 있는 자식이 없는 경우 -> error 발생
		em.remove(team);
		
		System.out.println("1---------------------------------");
		transation.commit();
		System.out.println("2---------------------------------");

		em.close();
		emf.close();
		
//		System.out.println("team.getMemberList = " + team.getMemberList());
		System.out.println("-----JPA 끝-----");
	}
}