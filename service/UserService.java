package MrcProject6.MrcProject6.service;

import MrcProject6.MrcProject6.model.UserInfo;
import MrcProject6.MrcProject6.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Optional<UserInfo> findByUserId(String userId) {
        // 사용자 아이디를 이용하여 사용자를 데이터베이스에서 찾아옵니다.
        return userRepository.findByUserId(userId);
    }
}
