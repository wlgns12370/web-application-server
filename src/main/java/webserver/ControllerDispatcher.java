package webserver;

import java.util.HashMap;
import java.util.Map;

import webserver.controller.Controller;
import webserver.controller.RenderingController;
import webserver.controller.UserController;
import webserver.entity.RequestEntity;

public class ControllerDispatcher {
    private Map<String, Controller> controllerMap = new HashMap<>();

    public ControllerDispatcher() {
        controllerMap.put("/index.html", new RenderingController());
        controllerMap.put("/user/form.html", new RenderingController());
        controllerMap.put("/user/login.html", new RenderingController());
        controllerMap.put("/user/create", new UserController());
    }

    public byte[] dispatch(RequestEntity request) {
        Controller controller = controllerMap.get(request.getUri());
        if (controller != null) {
            return controller.handleRequest(request);
        }
        return "404 Not Found".getBytes();
    }
}
