package shop.mtcoding.blog.User;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import shop.mtcoding.blog.user.User;
import shop.mtcoding.blog.user.UserJAPRepository;

import java.util.Optional;

@DataJpaTest
public class UserJPARepositoryTest {

    @Autowired
    private UserJAPRepository userJAPRepository;

    @Test
    public void findUsernameAndpassword_test(){
        //given
        String useranme = "ssar";
        String password = "1234";
        //when
        Optional<User> user = userJAPRepository.findByUsernameAndPassword(useranme,password);
        //thne
        System.out.println(user);
    }
}
