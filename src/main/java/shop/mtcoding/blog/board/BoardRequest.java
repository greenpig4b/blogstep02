package shop.mtcoding.blog.board;

import lombok.Data;
import shop.mtcoding.blog.user.User;

public class BoardRequest {

    @Data
    // 업데이트 DTO 생성
    public static class UpdateDTO{
        private String title;
        private String content;
    }


    @Data
    // 세이브 DTO 생성
    public static class SaveDTO{
        private String title;
        private String content;

        //DTO를 클라이언트로 부터 받아서, PC에 전달하기 위해서 사용
        public Board toEntity(User user){
            return Board.builder()
                    .title(title)
                    .content(content)
                    .user(user)
                    .build();
        }
    }
}
