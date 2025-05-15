package shop.mtcoding.blog._core.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class CorsFilter implements Filter {
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String origin = request.getHeader("Origin");
        log.debug("origin : " + origin);

        response.setHeader("Access-Control-Allow-Origin", origin);
//        response.setHeader("Access-Control-Expose-Headers", "Authorization"); // 이 헤더 응답을 자바스크립트로 접근 할 수 있도록 허용할지
        response.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET, DELETE, OPTIONS");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Key, Content-Type, Accept, Authorization"); // 서버가 요청을 받을 때 이 헤더값은 허용해서 받는다
        // X-key -> 내가 임의로 만든 헤더 값은 X- 를 붙여서 만든다. 약속이다
        response.setHeader("Access-Control-Allow-Credentials", "true"); // 쿠키의 세션값을 허용

        // 위의 응답 헤더값을 브라우저가 읽어서

        // Preflight 요청을 허용하고 바로 응답하는 코드
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            response.setStatus(200);
        } else {
            chain.doFilter(req, res);
        }
    }
}