package MrcProject6.MrcProject6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "board")
@Getter
@Setter
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num") // 게시글 번호
    private Long boardNum;

    @Column(name = "title") // 게시글 제목
    private String title;

    @Column(name = "content") // 게시글 내용
    private String content;

    @Temporal(TemporalType.TIMESTAMP) // 등록 날짜
    @Column(name = "regdate")
    private Date regdate;

    @Temporal(TemporalType.TIMESTAMP) // 수정 날짜
    @Column(name = "updatadate")
    private Date updatadate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_nick", referencedColumnName = "user_nick")
    private UserInfo writer;  //writer 필드는 게시글의 작성자를 나타낸다
                                // UserInfo 엔티티와의 관계를 나타내기 위해 @ManyToOne 관계로 설정

    // 생성자
    public Board() {

    }
}
