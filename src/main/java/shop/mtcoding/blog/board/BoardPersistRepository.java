package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardPersistRepository {

    private final EntityManager em;

    @Transactional
    public Board save(Board board){
        // 비영속 객체
        em.persist(board);
        // board - > 영속객체
        return board;
    }

    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc",Board.class);
        return  query.getResultList();
    }

    //보드상세보기
    public Board findById(Integer id){
        Board board = em.find(Board.class,id);

        return board;
    }

    //보드삭제하기
    @Transactional
    public void deleteById(Integer id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }
}
