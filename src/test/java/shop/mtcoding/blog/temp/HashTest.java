package shop.mtcoding.blog.temp;

import org.junit.jupiter.api.Test;
import org.mindrot.jbcrypt.BCrypt;


public class HashTest {
    @Test
    public void encode_test() {
        //$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW
        //$2a$10$uSWnzIA81.lJuqyIDeaLNeREhFeNpgxynmabL2BSjmSqsC4h7zGWq
        // given
        String pw = "1234";

        // when
        String encPw = BCrypt.hashpw(pw, BCrypt.gensalt());

        // eye
        System.out.println(encPw);
    }

    @Test
    public void decode_test() {
        //$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW
        //$2a$10$uSWnzIA81.lJuqyIDeaLNeREhFeNpgxynmabL2BSjmSqsC4h7zGWq
        // given
        String dbPw = "$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW";
        String pw = "1234";

        // when
        String encPw = BCrypt.hashpw(pw, BCrypt.gensalt());

        if (dbPw.equals(encPw)) {
            System.out.println("비밀번호가 같아요");
        } else {
            System.out.println("비밀번호가 달라요");
        }

        // eye
        System.out.println(encPw);
    }

    @Test
    public void decodeV2_test() {
        //$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW
        //$2a$10$uSWnzIA81.lJuqyIDeaLNeREhFeNpgxynmabL2BSjmSqsC4h7zGWq
        // given
        String dbPw = "$2a$10$eKSTNIZGZFLt8TojTyhXgeRth0buSvmm8VS/k6d7yhfXJt44ILBoW";
        String pw = "1234";

        // when
        Boolean isSame = BCrypt.checkpw(pw, dbPw);

        // eye
        System.out.println(isSame);
    }
}
