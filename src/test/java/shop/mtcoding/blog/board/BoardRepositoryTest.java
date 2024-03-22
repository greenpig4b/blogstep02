package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class BoardRepositoryTest {

    @Autowired
    private EntityManager em;

    @Test
    public void findById_test(){
        Integer id = 1;

        Board board = em.find(Board.class,id);

        System.out.println(board);
    }
}
