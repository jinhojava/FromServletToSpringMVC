package mvc1servlet.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyHttpApiStringServlet",urlPatterns = "/request-body-string")
public class RequestBodyHttpApiStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();//메세지 바디의 내용을 바이트코드로 얻을 수 있다.
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//바이트를 문자로 변환시 어떤 인코딩인지 알려줘야한다 반대도 마찬가지

        System.out.println("messagebody = " + messagebody);

        response.getWriter().write("ok");
    }
}
