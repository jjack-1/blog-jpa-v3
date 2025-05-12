package shop.mtcoding.blog;

/*
 * 리스트 = [1,2,3] - 가변
 * 튜플 = (1,2,3) - 불변
 * */
public enum ApplyEnum { // 열겨형
    PASS("합격"), FAIL("불합격"); // 이 코드 gpt에 넣어서 설명 작성

    public String value;

    ApplyEnum(String value) {
        this.value = value;
    }
}
