package shop.mtcoding.blog.board;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BoardRepository {

    private final EntityManager em;

    // 게시글 상세보기
    public Board fintById(Integer id){
        Board board = em.find(Board.class,id);
        return board;
    }

    //게시글 목록보기
    public List<Board> findAll(){
        Query query = em.createQuery("select b from Board b order by b.id desc", Board.class);
        List<Board> boardList = query.getResultList();
        return boardList;
    }

    //게시글 쓰기
    @Transactional
    public Board save(Board board){
        em.persist(board);

        return board;
    }

    //게시글 삭제
    @Transactional
    public void delete(Integer id){
        Query query = em.createQuery("delete from Board b where b.id = :id");
        query.setParameter("id",id);
        query.executeUpdate();
    }

    //게시글 수정
    @Transactional
    public Board updateById(Integer id,BoardRequest.UpdateDTO reqDTO){
        Board board = fintById(id);
        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
    }
}