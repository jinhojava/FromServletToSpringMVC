package mvc1servlet.servlet.web.frontcontroller.v1.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1servlet.servlet.web.frontcontroller.v1.ControllerV1;

import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);//컨트롤러에서 뷰로 이동할 때 사용, 이 경로로 이동할거야
        dispatcher.forward(request, response);//서블릿에서 jsp호출할 수 있다.
    }
}
