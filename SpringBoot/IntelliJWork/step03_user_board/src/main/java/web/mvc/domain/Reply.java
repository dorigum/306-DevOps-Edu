package web.mvc.domain;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "reply_rno_seq"*/)
    //@SequenceGenerator(name="reply_rno_seq" , allocationSize = 1 , sequenceName = "reply_rno_seq")
	private Long rno; // 댓글 번호
	
	private String content; // 댓글 내용
	
	@CreationTimestamp
	private LocalDateTime insertDate;
	
	@ManyToOne(fetch = FetchType.LAZY) // 지연 로딩!!
	@JoinColumn(name = "free_bno") // FK 설정
	private FreeBoard freeBoard;
}