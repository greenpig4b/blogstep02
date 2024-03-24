package shop.mtcoding.blog.board;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog._core.errors.exception.Exception404;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardService {
    private final BoardJPARepository boardJPARepository;

    public Board findById(Integer boardId){
        //1 조회 및 예외처리
        Board board = boardJPARepository.findById(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        return board;
    }

    @Transactional
    public Board update(Integer id, Integer sessionUserId, BoardRequest.UpdateDTO reqDTO){
        //조회 및 예외처리
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));

        //권한처리
        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("수정할 권한이 없습니다.");
        }

        board.setTitle(reqDTO.getTitle());
        board.setContent(reqDTO.getContent());

        return board;
        //더티채킹

    }
    @Transactional
    public void delete(Integer id,Integer sessionUserId){
        //조회 및 예외처리
        Board board = boardJPARepository.findById(id)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다."));
        //권한처리
        if (sessionUserId != board.getUser().getId()){
            throw new Exception403("수정할 권한이 없습니다.");
        }
        boardJPARepository.deleteById(id);

    }

    @Transactional
    public void write(BoardRequest.SaveDTO reqDTO, User sessionUser){
        boardJPARepository.save(reqDTO.toEntity(sessionUser));
    }


    public Board detail(Integer boardId, User sessionUser) {
        Board board = boardJPARepository.findbyIdJoinUser(boardId)
                .orElseThrow(() -> new Exception404("게시글을 찾을 수 없습니다"));

        boolean boardOwner = false;
        if(sessionUser != null){
            if(sessionUser.getId() == board.getUser().getId()){
                boardOwner = true;
            }
        }

        board.setBoardOwner(boardOwner);

        return board;
    }

    public List<Board> findAll(){
        Sort sort = Sort.by(Sort.Direction.DESC,"id");
        List<Board> boardList = boardJPARepository.findAll(sort);

        return boardList;
    }

}
