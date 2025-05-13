package shop.mtcoding.blog.board;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog._core.error.ex.ExceptionApi404;

@Import(BoardRepository.class)
@DataJpaTest // EntityManager, PC
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findByIdJoinUserAndReplies_test() {
        // given
        Integer id = 1;

        // when
        Board boardPS = boardRepository.findByIdJoinUserAndReplies(id)
                .orElseThrow(() -> new ExceptionApi404("자원을 찾을 수 없습니다"));

        // eye
        System.out.println(boardPS);
    }
}
