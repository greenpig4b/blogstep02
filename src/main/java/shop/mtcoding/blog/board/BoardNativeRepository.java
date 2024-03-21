package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardNativeRepository {
    private final EntityManager em;

    @Transactional
    public void updateById() {

    }

    public Board findById() {

        return null;
    }

    public List<Board> findAll() {
        Query query = em.createNativeQuery("SELECT * FROM BOARD_TB order by id desc",Board.class);
        List<Board> boardList = query.getResultList();

        return boardList;
    }

    @Transactional
    public void save() {

    }

    @Transactional
    public void deleteById() {

    }
}
