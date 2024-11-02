package webserver.controller;

import db.DataBase;
import model.User;
import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class UserController implements Controller {

    @Override
    public ApiResult handleRequest(final RequestEntity request) {
        User user = new User(
            request.getQueryString().get("userId"),
            request.getQueryString().get("password"),
            request.getQueryString().get("name"),
            request.getQueryString().get("email")
        );
        DataBase.addUser(user);
        System.out.println(user.toString());
        return ApiResult.from(HttpStatus.FOUND, null, null);
    }
}