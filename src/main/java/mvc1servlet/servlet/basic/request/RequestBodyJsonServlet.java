package mvc1servlet.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1servlet.servlet.basic.HelloData;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@WebServlet(name = "requestBodyJsonServlet",urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messagebody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        System.out.println("messagebody = " + messagebody);
        HelloData helloData = objectMapper.readValue(messagebody, HelloData.class);//json을 hellodata객체로변환

        System.out.println("helloData = " + helloData.getUsername());
        System.out.println("helloData = " + helloData.getAge());

        response.getWriter().write("ok");
    }
}
