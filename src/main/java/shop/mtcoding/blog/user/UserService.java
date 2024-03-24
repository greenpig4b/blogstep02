package shop.mtcoding.blog.user;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import shop.mtcoding.blog._core.errors.exception.Exception400;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog._core.errors.exception.Exception404;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserJAPRepository userJAPRepository;


    //회원가입
    @Transactional
    public void join(UserRequest.JoinDTO reqDTO){
        Optional<User> userOP = userJAPRepository.findByUsername(reqDTO.getUsername());

        //유저네임 중복검사
        if (userOP.isPresent()){
            throw new Exception400("이미사용중인 아이디입니다.");
        }
        //2. 회원가입
        userJAPRepository.save(reqDTO.toEntity());
    }

    //로그인
    public User login(UserRequest.LoginDTO reqDTO){
        User sessionUser = userJAPRepository.findByUsernameAndPassword(reqDTO.getUsername(),reqDTO.getPassword())
                .orElseThrow(() -> new Exception401("인증되지 않았습니다."));
        return sessionUser;
    }

    //회원조회
    public User findByUser(Integer id){
        User user = userJAPRepository.findById(id).orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));

        return user;
    }

    //회원수정
    @Transactional
    public User update(Integer id,UserRequest.UpdateDTO reqDTO){
        User user = userJAPRepository.findById(id)
                .orElseThrow(() -> new Exception404("회원정보를 찾을 수 없습니다."));

        user.setPassword(reqDTO.getPassword());

        return user;
    }
}
