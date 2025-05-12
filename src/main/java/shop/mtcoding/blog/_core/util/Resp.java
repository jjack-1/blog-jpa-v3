package shop.mtcoding.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

// 1. ResponseEntity는 @Controller, @RestController 상관없이 무조건 데이터(json)를 응답
// 2. ResponseEntity는 상태코드를 리턴할 수 있다
// 3. 인수(body, 상태코드)
// 4. ResponseEntity는 spring이 만들어 주는 것
// 5. 디스패처 서블릿이 ResponseEntity 안에 있는 body 와 status 클래스를 꺼내서 응답한다. status 클래스에서 숫자를 꺼내서 setStatus 에 넣어 응답한다
@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    private Resp() {
    } // 기본생성자를 막아서 setter를 사용못하게 한다

    // ResponseEntity 형태로 바로 리턴 (성공)
    public static <B> ResponseEntity<Resp<B>> ok(B body) {
        Resp<B> resp = new Resp<>(200, "성공", body);
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    // ResponseEntity 형태로 바로 리턴 (실패)
    public static ResponseEntity<Resp<?>> fail(HttpStatus status, String msg) {
        Resp<?> resp = new Resp<>(status.value(), msg, null);
        return new ResponseEntity<>(resp, status);
    }

    // 스프링의 도움을 받지 않을 때
    public static Resp<?> fail(Integer status, String msg) {
        Resp<?> resp = new Resp<>(status, msg, null);
        return resp;
    }
}