package MrcProject6.MrcProject6.controller;

import MrcProject6.MrcProject6.model.UserInfo;
import MrcProject6.MrcProject6.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

// Lombok을 사용하여 로그 생성
@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    // Logger 객체 생성 //본인 확인용
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 사용자 정보를 저장 엔드포인트
    // POST 요청을 통해 사용자 정보를 저장
    @PostMapping("/save")
    public ResponseEntity<String> saveUser(@RequestBody UserInfo userinfo) {
        // 사용자 아이디가 이미 존재하는지 확인
        if (userRepository.existsByUserId(userinfo.getUserId())) {
            // 사용자 아이디가 이미 존재하는 경우 CONFLICT 상태 코드 반환
            return ResponseEntity.status(HttpStatus.CONFLICT).body("유저 아이디 이미 존재");
        }

        try {
            // 사용자 정보 저장
            userRepository.save(userinfo);
            // 회원가입 성공 로그 출력
            String successMessage = "회원가입 성공!!! user_id :  " + userinfo.getUserId();
            logger.info(successMessage);
            // 성공 응답 반환
            return ResponseEntity.ok().body(successMessage);
        } catch (Exception e) {
            // 예외 발생 시 내부 서버 오류 상태 코드 반환
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error saving user: " + e.getMessage());
        }
    }
}
