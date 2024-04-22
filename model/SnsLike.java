package MrcProject6.MrcProject6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "snslike")
@Getter
@Setter
public class SnsLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "heart")
    private Long heart;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "post_id")
    private Long postId;

    // 추가적으로 상태를 표시할 필드 (꽉 찬 하트인지, 빈 하트인지)
    @Transient
    private boolean liked;

    // 생성자
    public SnsLike() {

    }

    // 좋아요 상태를 변경하는 메소드
    public void toggleLike() {
        this.liked = !this.liked;
    }
}
