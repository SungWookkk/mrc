package MrcProject6.MrcProject6.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_info")
@Getter
@Setter
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id") // 컬럼명을 명시적으로 지정
    private String userId;

    @Column(name = "user_pw")
    private String userPassword;

    @Column(name = "user_phone")
    private String userPhoneNumber;

    @Column(name = "user_email")
    private String userEmail;


    @Column(name = "user_nick", unique = true)
    private String userNick;

    public UserInfo() {
    }

    // 매개변수를 받는 생성자
    public UserInfo(String userNick) {
        this.userNick = userNick;
    }
}
