package MrcProject6.MrcProject6.repository;

import MrcProject6.MrcProject6.model.Board;
import MrcProject6.MrcProject6.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findByWriter(UserInfo writer);

}
