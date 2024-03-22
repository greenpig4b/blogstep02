package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardPersistRepository boardPersistRepository;
    private final BoardRepository boardRepository;
//    @Transactional
//    @PostMapping("/board/{id}/update")
//    public String update(@PathVariable Integer id,BoardRequest.UpdateDTO resqDTO){
//
//        boardPersistRepository.updateById(id,resqDTO);
//        return "redirect:/board/"+id;
//    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(@PathVariable Integer id, HttpServletRequest request){
        Board board = boardPersistRepository.findById(id);
        request.setAttribute("board",board);
        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(@PathVariable Integer id){
        boardPersistRepository.deleteById(id);

        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(Board board){
        boardPersistRepository.save(board);

        return "redirect:/";
    }

    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardPersistRepository.findAll();
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
        request.setAttribute("board",board);
        return "board/detail";
    }

}
