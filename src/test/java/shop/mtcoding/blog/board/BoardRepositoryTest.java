package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import java.util.List;
@Import(BoardRepository.class) // 테스트할때 붙여야함!
@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private EntityManager em;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void findById_test(){
        Integer id = 1;

        Board board = em.find(Board.class,id);

        System.out.println(board);
    }

    @Test
    public void findAll_test() {
        // given

        // when
        List<Board> boardList = boardRepository.findAll();
        boardList.forEach(board -> {
            System.out.println(board.getUser().getUsername());
        });

        // then
    }
}
