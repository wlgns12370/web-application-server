package webserver;

import java.util.HashMap;
import java.util.Map;

import webserver.controller.Controller;
import webserver.controller.LoginController;
import webserver.controller.RenderingController;
import webserver.controller.UserController;
import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class ControllerDispatcher {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public ControllerDispatcher() {
        controllerMap.put("/index.html", new RenderingController());
        controllerMap.put("/user/form.html", new RenderingController());
        controllerMap.put("/user/login.html", new RenderingController());
        controllerMap.put("/user/create", new UserController());
        controllerMap.put("/user/login.html", new RenderingController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/login_failed.html", new RenderingController());
    }

    public ApiResult dispatch(RequestEntity request) {
        Controller controller = controllerMap.get(request.getUri());
        if (controller != null) {
            return controller.handleRequest(request);
        }
        return ApiResult.from(HttpStatus.NOT_FOUND, "올바르지 않은 URL입니다.".getBytes(), null);
    }
}
