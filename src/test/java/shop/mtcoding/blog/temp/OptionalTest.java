package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;

import java.util.Optional;

/*
Optional.of(value)	절대 null이 아니어야 함
Optional.ofNullable(value)	null일 수도 있음
Optional.empty()	빈 Optional 생성

get()	값 꺼냄 (값이 없으면 예외 발생)
orElse(default)	값이 없으면 기본값 리턴
orElseGet(Supplier)	값이 없으면 람다 결과 리턴
orElseThrow()	값이 없으면 예외 발생

isPresent()	값이 있는지 확인
ifPresent(Consumer)	값이 있으면 처리
filter(Predicate)	조건에 맞는 값만 유지
 */
public class OptionalTest {

    @Test
    public void t1() {
        String name = "metacoding";
        // 1. null 안정성을 제공함. xx.get() -> xx 가 null 이면 nullpointException 이 터진다
        // Optional -> Promise 같은 선물박스다
        Optional<String> opt = Optional.of(name); // <- of : 넣을 때 에러남. of 에서 터짐
        System.out.println(opt.get());

        Optional<String> opt2 = Optional.ofNullable(name); // ofNullable -> 넣을 때 에러가 안남

        if (opt2.isPresent()) { // 존재하는지 확인 가능
            System.out.println(opt2.get()); // <- get() 에서 터짐
        } else {
            System.out.println("선물박스에 값이 없어요");
        }

    }

    @Test
    public void t2() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        try {
            String result = opt.orElseThrow(() -> new RuntimeException("값이 없습니다")); // 값이 있으면 넣고, 없으면 throw 한다
            System.out.println(result);
        } catch (RuntimeException e) {
            System.out.println("터져도 괜찮아");
        }
    }

    @Test
    public void t3() {
        String name = null;
        Optional<String> opt = Optional.ofNullable(name);
        String result = opt.orElseGet(() -> "metacoding"); // 값이 없으면 기본값을 준다
        System.out.println(result);
    }
}
