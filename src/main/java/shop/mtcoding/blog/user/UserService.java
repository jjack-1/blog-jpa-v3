package shop.mtcoding.blog.user;

import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import shop.mtcoding.blog._core.error.ex.ExceptionApi400;
import shop.mtcoding.blog._core.error.ex.ExceptionApi401;
import shop.mtcoding.blog._core.error.ex.ExceptionApi404;
import shop.mtcoding.blog._core.util.JwtUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

// 비지니스로직, 트랜잭션처리, DTO 완료
@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;

    // RestAPI 규칙 1 -> insert 는 그 행을 DTO 에 담아 돌려줘야 한다
    @Transactional
    public UserResponse.DTO 회원가입(UserRequest.JoinDTO reqDTO) {

        Optional<User> userOP = userRepository.findByUsername(reqDTO.getUsername());
        if (userOP.isPresent()) throw new ExceptionApi400("이미 존재하는 username 입니다");

        String encPassword = BCrypt.hashpw(reqDTO.getPassword(), BCrypt.gensalt());
        reqDTO.setPassword(encPassword);

        User userPS = userRepository.save(reqDTO.toEntity());
        return new UserResponse.DTO(userPS);

    }

    // TODO -> A4용지에다가 id, username 적어, A4용지를 서명함, A4용지를 돌려주기
    public UserResponse.TokenDTO 로그인(UserRequest.LoginDTO reqDTO) {
        User userPS = userRepository.findByUsername(reqDTO.getUsername())
                .orElseThrow(() -> new ExceptionApi401("유저네임 혹은 비밀번호가 틀렸습니다"));

        Boolean isMatched = BCrypt.checkpw(reqDTO.getPassword(), userPS.getPassword());

        if (!isMatched) {
            throw new ExceptionApi401("유저네임 혹은 비밀번호가 틀렸습니다");
        }

        // 토큰 생성
        String accessToken = JwtUtil.create(userPS);
        String refreshToken = JwtUtil.createRefresh(userPS);

        // TODO -> RestAPI 전환 끝나면 수업함
        // DB에 DevicePrint서명값(LoginDTO), IP(request), User-Agent(request), RefreshToken 을 같이 (별도의 Table)저장
        // refresh 구현하는 방법

        return UserResponse.TokenDTO.builder().accessToken(accessToken).refreshToken(refreshToken).build();
    }

    public Map<String, Object> 유저네임중복체크(String username) {
        Optional<User> userOP = userRepository.findByUsername(username);
        Map<String, Object> dto = new HashMap<>();

        if (userOP.isPresent()) {
            dto.put("available", false);
        } else {
            dto.put("available", true);
        }
        return dto;
    }

    // TODO RestAPI 규칙 3 -> update 된 data 도 돌려줘야 한다. 변경이 된 데이터도 돌여줘야 한다
    @Transactional
    public UserResponse.DTO 회원정보수정(UserRequest.UpdateDTO reqDTO, Integer userId) {

        User userPS = userRepository.findById(userId)
                .orElseThrow(() -> new ExceptionApi404("자원을 찾을 수 없습니다"));

        userPS.update(reqDTO.getPassword(), reqDTO.getEmail()); // 영속화된 객체의 상태변경
        return new UserResponse.DTO(userPS); // 리턴한 이유는 세션을 동기화해야해서!!
    } // 더티체킹 -> 상태가 변경되면 update을 날려요!!
}
