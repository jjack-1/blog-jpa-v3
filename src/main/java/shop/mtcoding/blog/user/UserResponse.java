package shop.mtcoding.blog.user;

import lombok.Data;

public class UserResponse {

    // 가장 기본이 되는 DTO 는 그냥 DTO 로 만든다(User -> DTO)
    // RestAPI 규칙 2 -> DTO 에 민감 정보 삭제, 날짜는 String (날짜타입을 공부할 때까지)
    @Data
    public static class DTO {
        private Integer id;
        private String username;
        private String email;
        private String createdAt;

        public DTO(User user) {
            this.id = user.getId();
            this.username = user.getUsername();
            this.email = user.getEmail();
            this.createdAt = user.getCreatedAt().toString();
        }
    }

}
