package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.assertj.core.api.Assertions;
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

    @Test
    public void delete_test(){
        //given
        Integer id = 1;
        //when
        boardRepository.delete(id);
        em.flush();

        //given
        List<Board> boardList = boardRepository.findAll();
        System.out.println(boardList.size());
    }

    @Test
    public void update_test(){
        //given
        Integer id  = 1;
        BoardRequest.UpdateDTO reqDTO = new BoardRequest.UpdateDTO();
        reqDTO.setTitle("제목1ㄴㅇㄴㅇ");
        reqDTO.setContent("제목1ㄴㅇㄴㅇ");

        //when
        Board board = boardRepository.updateById(id,reqDTO);
        //then
        System.out.println(board);
    }

}
