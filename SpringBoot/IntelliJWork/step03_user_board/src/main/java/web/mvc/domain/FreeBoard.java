package web.mvc.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(exclude = "repliesList")
public class FreeBoard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "free_bno_seq")
    @SequenceGenerator(name = "free_bno_seq", allocationSize = 1, sequenceName = "free_bno_seq")
    private Long bno;

    private String subject;
    private String writer;

    @Column(length = 500)
    private String content;

    private String password;
    private int readnum;

    @CreationTimestamp
    private LocalDateTime insertDate;

    @UpdateTimestamp
    private LocalDateTime updateDate;

    @OneToMany(mappedBy = "freeBoard")
    @Builder.Default
    private List<Reply> repliesList = new ArrayList<>();

    public FreeBoard(Long bno) {
        this.bno = bno;
    }
}
