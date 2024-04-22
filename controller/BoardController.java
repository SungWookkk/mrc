package MrcProject6.MrcProject6.controller;

import MrcProject6.MrcProject6.controller.JwtUtils;
import MrcProject6.MrcProject6.model.Board;
import MrcProject6.MrcProject6.model.UserInfo;
import MrcProject6.MrcProject6.repository.BoardRepository;
import MrcProject6.MrcProject6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySources;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;
    private final JwtUtils jwtUtils;

    @Autowired
    public BoardController(BoardRepository boardRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.boardRepository = boardRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    // 모든 게시글 조회
    @GetMapping("/all")
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }

    // 게시글 작성
    // 게시글 작성
    @PostMapping("/write")
    public ResponseEntity<String> writeBoard(@RequestBody Board board, @RequestHeader("Authorization") String token) {
        // 클라이언트에서 전송한 요청에 대해 토큰을 검증하여 인증 상태 확인
        if (token == null || !token.startsWith("Bearer ")) {
            // 토큰이 없거나 형식이 잘못된 경우에는 오류 응답을 반환
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized: Invalid token");
        }

        // 토큰에서 사용자 정보 추출
        String userNick = jwtUtils.extractUserNickFromToken(token);

        // 작성자 정보 설정
        UserInfo writer = userRepository.findByUserNick(userNick)
                .orElseThrow(() -> new IllegalArgumentException("User not found with userNick: " + userNick));
        board.setWriter(writer);


        // 게시글 저장
        boardRepository.save(board);

        return ResponseEntity.ok().body("Board created successfully!");
    }




    // 게시글 수정
    @PutMapping("/update/{boardNum}")
    public ResponseEntity<String> updateBoard(@PathVariable Long boardNum, @RequestBody Board updatedBoard) {
        Optional<Board> optionalBoard = boardRepository.findById(boardNum);
        if (optionalBoard.isPresent()) {
            Board board = optionalBoard.get();
            board.setTitle(updatedBoard.getTitle());
            board.setContent(updatedBoard.getContent());
            board.setUpdatadate(new Date());
            boardRepository.save(board);
            return ResponseEntity.ok().body("Board updated successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Board not found with id: " + boardNum);
        }
    }

    // 게시글 삭제
    @DeleteMapping("/delete/{boardNum}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long boardNum) {
        boardRepository.deleteById(boardNum);
        return ResponseEntity.ok().body("Board deleted successfully!");
    }

    // 특정 게시글 조회
    @GetMapping("/{id}") // URL에서 id에 해당하는 게시글을 조회
    public ResponseEntity<Board> getBoardById(@PathVariable Long id) {
        Optional<Board> optionalBoard = boardRepository.findById(id);
        if (optionalBoard.isPresent()) {
            return ResponseEntity.ok().body(optionalBoard.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/user/{userNick}")
    public List<Board> getBoardsByWriter(@PathVariable String userNick) {
        Optional<UserInfo> writerOptional = userRepository.findByUserNick(userNick);
        if (writerOptional.isPresent()) {
            UserInfo writer = writerOptional.get();
            return boardRepository.findByWriter(writer);
        } else {
            // 해당하는 사용자가 없을 경우 처리
            return Collections.emptyList(); // 예시로 빈 리스트 반환
        }
    }
}
