package shop.mtcoding.blog.temp;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.user.User;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class TokenTest {

    @Test
    public void create_test() {
        // given
        User user = User.builder()
                .id(1)
                .username("ssar")
                .password("$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW")
                .email("ssar@nate.com")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        String jwt = JWT.create()
                .withSubject("blogv3") // 토큰 이름 필수!
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 만료시간
                .withClaim("id", user.getId()) // 넣고 싶은 정보
                .withClaim("username", user.getUsername()) // 넣고 싶은 정보
                .sign(Algorithm.HMAC256("metacoding")); // 마지막 해쉬생성

        // eye
        System.out.println(jwt);
    }

    @Test
    public void verify() {
        // given
        User user = User.builder()
                .id(1)
                .username("ssar")
                .password("$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW")
                .email("ssar@nate.com")
                .createdAt(Timestamp.valueOf(LocalDateTime.now()))
                .build();

        // when
        String jwt = JWT.create()
                .withSubject("blogv3") // 토큰 이름 필수!
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60)) // 만료시간
                .withClaim("id", user.getId()) // 넣고 싶은 정보
                .withClaim("username", user.getUsername()) // 넣고 싶은 정보
                .sign(Algorithm.HMAC256("metacoding")); // 마지막 해쉬생성

        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC256("metacoding")).build().verify(jwt);
        Integer id = decodedJWT.getClaim("id").asInt();
        String username = decodedJWT.getClaim("username").asString();

        System.out.println(id);
        System.out.println(username);
    }
}
