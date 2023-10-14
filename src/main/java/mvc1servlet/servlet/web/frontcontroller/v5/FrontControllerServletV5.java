package mvc1servlet.servlet.web.frontcontroller.v5;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mvc1servlet.servlet.web.frontcontroller.ModelView;
import mvc1servlet.servlet.web.frontcontroller.MyView;
import mvc1servlet.servlet.web.frontcontroller.v3.Controller.MemberFormControllerV3;
import mvc1servlet.servlet.web.frontcontroller.v3.Controller.MemberListControllerV3;
import mvc1servlet.servlet.web.frontcontroller.v3.Controller.MemberSaveControllerV3;
import mvc1servlet.servlet.web.frontcontroller.v4.controller.MemberFormControllerV4;
import mvc1servlet.servlet.web.frontcontroller.v4.controller.MemberListControllerV4;
import mvc1servlet.servlet.web.frontcontroller.v4.controller.MemberSaveControllerV4;
import mvc1servlet.servlet.web.frontcontroller.v5.adapter.ControllerV3HandlerAdapter;
import mvc1servlet.servlet.web.frontcontroller.v5.adapter.ControllerV4HandlerAdapter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "frontControllerServletV5", urlPatterns = "/front-controller/v5/*")
public class FrontControllerServletV5 extends HttpServlet {

    private final Map<String, Object> handlerMappingMap = new HashMap<>();
    private final List<MyHandlerAdapter> handlerAdapters = new ArrayList<>();//어댑터가 여러개 담겨있고 그 중 하나를 꺼내찾아써야한다.

    public FrontControllerServletV5(){
        initHandlerMappingMap();
        initHandlerAdapters();
    }

    private void initHandlerMappingMap() {
        handlerMappingMap.put("/front-controller/v5/v3/members/new-form", new MemberFormControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members/save", new MemberSaveControllerV3());
        handlerMappingMap.put("/front-controller/v5/v3/members", new MemberListControllerV3());

        handlerMappingMap.put("/front-controller/v5/v4/members/new-form", new MemberFormControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members/save", new MemberSaveControllerV4());
        handlerMappingMap.put("/front-controller/v5/v4/members", new MemberListControllerV4());
    }

    private void initHandlerAdapters() {

        handlerAdapters.add(new ControllerV3HandlerAdapter());
        handlerAdapters.add(new ControllerV4HandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Object handler = getHandler(request);

        if(handler == null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyHandlerAdapter adapter = getHandlerAdapter(handler);
        ModelView mv = adapter.handle(request, response, handler);

        String viewName =  mv.getViewName();//논리이름
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyHandlerAdapter getHandlerAdapter(Object handler) {
        for (MyHandlerAdapter adapter : handlerAdapters) {
            if (adapter.supports(handler)) {
                return adapter;
            }
        }
    throw new IllegalArgumentException("handler adapter를 찾을 수 없습니다 handler="+handler);
    }

    private Object getHandler(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        return handlerMappingMap.get(requestURI);
    }
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }
}
