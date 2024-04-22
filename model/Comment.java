package MrcProject6.MrcProject6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "comment")
@Getter
@Setter
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_id")
    private Long comId;

    @Column(name = "com_content")
    private String comContent;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "regdate")
    private Date regdate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "writer", referencedColumnName = "user_nick")
    private UserInfo writer;

    // 생성자
    public Comment() {
    }
}
