package webserver.controller;

import model.User;
import webserver.entity.ApiResult;
import webserver.entity.HttpStatus;
import webserver.entity.RequestEntity;

public class UserController implements Controller {

    @Override
    public ApiResult handleRequest(final RequestEntity request) {
        User user = new User(
            request.getQueryString().get("userId"),
            request.getQueryString().get("password"),
            request.getQueryString().get("name"),
            request.getQueryString().get("email")
        );
        System.out.println(user.toString());
        return ApiResult.from(HttpStatus.FOUND, null);
    }
}