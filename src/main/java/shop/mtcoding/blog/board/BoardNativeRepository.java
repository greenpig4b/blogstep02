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
    public void updateById(Integer id ,String username,String content,String title) {
        Query query = em.createNativeQuery("update board_tb set title =? ,username = ?,content=? where id=?");
        query.setParameter(1,title);
        query.setParameter(2,username);
        query.setParameter(3,content);
        query.setParameter(4,id);

        query.executeUpdate();
    }

    public Board findById(Integer id) {
        Query query = em.createNativeQuery("select  * from board_tb where id = ?",Board.class);
        query.setParameter(1,id);
        Board board  = (Board) query.getSingleResult();
        return board;
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
    public void deleteById(Integer id) {
        Query query = em.createNativeQuery("delete from board_tb where id = ?");
        query.setParameter(1,id);

        query.executeUpdate();
    }
}
