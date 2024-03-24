package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding.blog._core.errors.exception.Exception403;
import shop.mtcoding.blog.user.User;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardRepository boardRepository;
    private final HttpSession session;
    @Transactional
    @PostMapping("/board/{id}/update")
    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO reqDTO){
        User user = (User) session.getAttribute("sesionUser");
        Board board = boardRepository.fintById(id);
        if (user.getId() != board.getUser().getId() ){
            throw new Exception403("게시글의 권한이 없습니다");
        }
        boardRepository.updateById(id,reqDTO);
        return "redirect:/board/"+id;
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request){
//        Board board = boardPersistRepository.findById(id);
//        request.setAttribute("board",board);

        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        User user = (User)session.getAttribute("sessionUser");
        Board board = boardRepository.fintById(id);

        if (user.getId() != board.getUser().getId()){
            throw new Exception403("삭제할 권한이 없습니다.");
        }
//        boardPersistRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(BoardRequest.SaveDTO reqDTO){
        User sessionUser = (User) session.getAttribute("sessionUser");
        boardRepository.save(reqDTO.toEntity(sessionUser));

        return "redirect:/";
    }

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardRepository.findAll();
        request.setAttribute("boardList",boardList);

        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {

        return "board/save-form";
    }


    @GetMapping("/board/{id}")
    public String detail(@PathVariable Integer id, HttpServletRequest request) {
        Board board = boardRepository.fintById(id);
        User user = (User) session.getAttribute("sessionUser");
        boolean isOwner = false;
        if (user != null){
            if (user.getId() == board.getUser().getId()){
                isOwner = true;
            }
        }

        request.setAttribute("isOwner",isOwner);
        request.setAttribute("board",board);
        return "board/detail";
    }

}
