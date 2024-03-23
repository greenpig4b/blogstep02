package shop.mtcoding.blog.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;
import shop.mtcoding.blog.user.UserRequest;

@Import(UserRepository.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void findUserAndPassword(){
        //given
        String username = "ssar";
        String password = "1234";

        //when
        User user = userRepository.findByUsernameAndPassword(username,password);
        //then
        Assertions.assertThat(user.getUsername()).isEqualTo("ssar");
    }


    @Test
    public void findById_test(){
        //given
        Integer id = 1;
        //when
        User user = userRepository.findById(id);
        System.out.println("결과값~~~~~~~~\n"+user);
        //then
    }


    @Test
    public void updateById(){
        //given
        Integer id  = 1;
        UserRequest.UpdateDTO updateDTO = new UserRequest.UpdateDTO();
        updateDTO.setPassword("11111");
        //given
        User user = userRepository.updateById(id, updateDTO);
        //given
        System.out.println(user);
    }

}
