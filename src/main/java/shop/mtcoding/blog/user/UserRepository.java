package shop.mtcoding.blog.user;


import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import static java.nio.file.Files.find;

@RequiredArgsConstructor
@Repository
public class UserRepository {

    private final EntityManager em;


    public User findByUsernameAndPassword(String username, String password){
        Query query = em.createQuery("select u from User u where u.username = :username and u.password = :password");
        query.setParameter("username",username);
        query.setParameter("password",password);

        return (User) query.getSingleResult();
    }


    //회원가입
    @Transactional
    public User save(User user){
        em.persist(user);
        return user;
    }

    //회원정보찾기
    public User findById(Integer id){
        User user = em.find(User.class,id);

        return user;
    }

    //회원정보 수정
    @Transactional
    public User updateById(Integer id, UserRequest.UpdateDTO reqDTO){
        User user = findById(id);
        user.setPassword(reqDTO.getPassword());

        return user;
    }

}
