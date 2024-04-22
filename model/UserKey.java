// UserKey.java

package MrcProject6.MrcProject6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_key") // 컬럼명을 명시적으로 지정
    private String userKey;

    @Column(name = "user_pw")
    private String password;

    @Column(name = "phone_number")
    private String phoneNumber;

    private String email;

    @Column(name = "user_id") // 추가된 사용자 아이디 필드
    private String userId;


}
