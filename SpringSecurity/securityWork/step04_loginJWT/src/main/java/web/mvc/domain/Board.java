package web.mvc.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity // 서버 실행 시에 해당 객체로 테이블 매핑 생성
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Board {
    @Id // PK를 해당 필드로 한다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 글 번호

    private String title; // 글 제목

    @Column(length = 100)
    private String content; // 글 내용

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_no")
    private Member member; // 작성자

    @CreationTimestamp
    private LocalDateTime regDate; // 등록일

    @UpdateTimestamp
    private LocalDateTime updateDate; // 수정일
}