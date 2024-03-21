package shop.mtcoding.blog.board;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BoardController {

    private final BoardNativeRepository boardNativeRepository;

    @PostMapping("/board/{id}/update")
    public String update(){

        return "redirect:/board";
    }

    @GetMapping("/board/{id}/update-form")
    public String updateForm(){

        return "board/update-form";
    }

    @PostMapping("/board/{id}/delete")
    public String delete(){

        return "redirect:/";
    }

    @PostMapping("/board/save")
    public String save(String title, String content, String userName){
        boardNativeRepository.save(title,content,userName);

        return "redirect:/";
    }


    @GetMapping("/" )
    public String index(HttpServletRequest request) {
        List<Board> boardList = boardNativeRepository.findAll();
        request.setAttribute("boardList",boardList);
        return "index";
    }

    @GetMapping("/board/save-form")
    public String saveForm() {
        return "board/save-form";
    }

    @GetMapping("/board/{id}")
    public String detail() {

        return "board/detail";
    }

}
