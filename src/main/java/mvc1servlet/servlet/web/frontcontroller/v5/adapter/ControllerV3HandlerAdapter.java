package mvc1servlet.servlet.web.frontcontroller.v5.adapter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1servlet.servlet.web.frontcontroller.ModelView;
import mvc1servlet.servlet.web.frontcontroller.v3.ControllerV3;
import mvc1servlet.servlet.web.frontcontroller.v5.MyHandlerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV3);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV3 controller = (ControllerV3) handler;
        Map<String,String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        return mv;
    }
    private static Map<String, String> createParamMap(HttpServletRequest request) {//httprequest에 있는 파라미터를 다뽑아서 paramMap으로 반환
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }

}

