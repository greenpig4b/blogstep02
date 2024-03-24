package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Sort;
import shop.mtcoding.blog.user.User;

import java.util.List;
import java.util.Optional;

@DataJpaTest
public class BoardJPARepositoryTest {

    @Autowired
    private BoardJPARepository boardJPARepository;

    @Autowired
    private EntityManager em;

    @Test
    public void findByJoinUser_test(){
        //given
        Integer id = 1;
        //when
        Optional<Board> board = boardJPARepository.findbyIdJoinUser(id);
        //then
        System.out.println(board);
    }

    @Test
    public void save_test(){
        //given
        User sessionUser = User.builder()
                .id(1).build();
        Board board = Board.builder()
                .title("타이틀1")
                .content("컨텐츠1")
                .user(sessionUser)
                .build();
        //when
        boardJPARepository.save(board);
        //then
        System.out.println(board);
    }

    @Test
    public void fintById_test(){
        //given
        Integer id = 1;
        //when
        Optional<Board> boardOP = boardJPARepository.findById(id);
        //then

        System.out.println(boardOP);
    }

    @Test
    public void findAll_test(){
        //given
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        //when
        List<Board> boardList = boardJPARepository.findAll(sort);
        //then
        System.out.println(boardList);
    }

    @Test
    public void deleteById_test(){
        //given
        Integer id = 1;
        //when
        boardJPARepository.deleteById(id);
        em.flush();
        //then
        List<Board> boardList = boardJPARepository.findAll();
        System.out.println(boardList);

    }
}
