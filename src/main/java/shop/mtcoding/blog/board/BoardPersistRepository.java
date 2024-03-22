package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

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
}
