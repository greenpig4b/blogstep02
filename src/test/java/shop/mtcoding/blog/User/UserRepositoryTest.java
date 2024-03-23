package shop.mtcoding.blog.User;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserRepository;

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
}
