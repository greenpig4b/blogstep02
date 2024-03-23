package shop.mtcoding.blog.user;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import shop.mtcoding.blog.board.BoardRequest;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO){
        User sessionUser =  userRepository.findByUsernameAndPassword(reqDTO.getUsername(),reqDTO.getPassword());
        session.setAttribute("sessionUser",sessionUser);

        return "redirect:/";
    }

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO){
        userRepository.save(reqDTO.toEntity());
        return "redirect:/";
    }

    @GetMapping("/join-form")
    public String joinForm() {

        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {
        return "user/login-form";
    }

    @GetMapping("/user/{id}/update-form")
    public String updateForm(@PathVariable Integer id) {

        return "user/update-form";
    }

    @PostMapping("/user/{id}/update")
    public String update(@PathVariable Integer id, UserRequest.UpdateDTO reqDTO){
        User user = (User) session.getAttribute("sessionUser");
        User newSessionUser = userRepository.updateById(user.getId(),reqDTO);
        session.setAttribute("sessionUser",newSessionUser);
        return "redirect:/";
    }



    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
