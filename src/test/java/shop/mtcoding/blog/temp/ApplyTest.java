package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import shop.mtcoding.blog.Apply;
import shop.mtcoding.blog.ApplyEnum;

public class ApplyTest {
/*
    @Test
    public void create_test() {
        Apply apply = new Apply(1, "홍길동", 3, ApplyStatus.PASS); // 인터페이스로 만들어 문자열을 선택해서 넣을 수 있다
        Apply apply2 = new Apply(2, "임꺽정", 3, "합격"); // 하지만 강제성이 없다. 그냥 문자열 넣을 수 있다
    }
*/

    @Test
    public void create_test2() {
        System.out.println(ApplyEnum.PASS.value); // PASS 글자 그대로 출력 됨
        System.out.println(ApplyEnum.FAIL.value);

        Apply apply = new Apply(1, "홍길동", 3, ApplyEnum.PASS);
        System.out.println(apply.getStatus());
    }
}
