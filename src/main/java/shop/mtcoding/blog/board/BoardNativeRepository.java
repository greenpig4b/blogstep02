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
    public void save(String title,String content, String userName) {
        Query query = em.createNativeQuery("insert into board_tb(title, content, username, created_at) values (?,?,?,now())");
        query.setParameter(1,title);
        query.setParameter(2,content);
        query.setParameter(3,userName);

        query.executeUpdate();
    }

    @Transactional
    public void deleteById() {

    }
}
