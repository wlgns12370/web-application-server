package webserver.controller;

import db.DataBase;
import model.User;
import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class LoginController implements Controller {

    @Override
    public ApiResult handleRequest(RequestEntity request) {
        User user = DataBase.findUserById(request.getQueryString().get("userId"));
        if (user == null) {
            return ApiResult.from(HttpStatus.UNAUTHORIZED, null, null);
        }
        if (user.getPassword().equals(request.getQueryString().get("password"))) {
            return ApiResult.from(HttpStatus.OK, null, true);    
        }
        return ApiResult.from(HttpStatus.UNAUTHORIZED, null, false);
    }
}
