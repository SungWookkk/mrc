package MrcProject6.MrcProject6.repository;

import MrcProject6.MrcProject6.model.UserInfo;
import MrcProject6.MrcProject6.model.UserKey;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    Optional<UserInfo> findByUserId(String userId);

    boolean existsByUserId(String userId);
    Optional<UserInfo> findByUserNick(String userNick);
}
