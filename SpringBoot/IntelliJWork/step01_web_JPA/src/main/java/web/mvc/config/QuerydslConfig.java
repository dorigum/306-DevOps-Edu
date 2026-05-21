package web.mvc.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    @PersistenceContext
    private EntityManager em;

    @Bean // <bean class = "JPAQueryFactory" id="jpaQueryFactory"/> 동일
    public JPAQueryFactory jpaQueryFactory() {
        System.out.println("QuerydslConfig의 jpaQueryFactory() 실행");
        System.out.println("EntityManager: " + em);
        return new JPAQueryFactory(em);
    }
}