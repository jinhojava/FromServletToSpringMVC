package mvc1servlet.servlet.web.frontcontroller.v3.Controller;

import mvc1servlet.servlet.web.frontcontroller.ModelView;
import mvc1servlet.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberFormControllerV3 implements ControllerV3 {
    @Override
    public ModelView process(Map<String, String> paramMap) {
        return new ModelView("new-form");
    }
}
