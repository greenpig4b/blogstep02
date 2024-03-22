package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class SaveDTO{
        private String title;
        private String content;
        private String username;

        public Board toEntity(){
            return new Board(title,content,username);
        }
    }

    @Data
    // 업데이트 DTO 생성
    public static class UpdateDTO{
        private String title;
        private String content;
        private String username;
    }

}
