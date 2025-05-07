package shop.mtcoding.blog._core.util;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Resp<T> {
    private Integer status;
    private String msg;
    private T body;

    private Resp() {
    } // 기본생성자를 막아서 setter를 사용못하게 한다

    public static <B> Resp<?> ok(B body) {
        return new Resp<>(200, "성공", body);
    }

    public static Resp<?> fail(Integer status, String msg) {
        return new Resp<>(status, msg, null);
    }
}