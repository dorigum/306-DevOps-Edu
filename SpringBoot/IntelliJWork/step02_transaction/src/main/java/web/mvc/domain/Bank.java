package web.mvc.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity // JPA를 관리하는 객체
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@DynamicUpdate
public class Bank {
    @Id // pk
    private String account; // 계좌 번호
    private int balance; // 잔액
}
