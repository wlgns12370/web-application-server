package webserver.controller;

import model.User;
import webserver.entity.RequestEntity;

public class UserController implements Controller {

    @Override
    public byte[] handleRequest(final RequestEntity request) {
        User user = new User(
            request.getQueryString().get("userId"),
            request.getQueryString().get("password"),
            request.getQueryString().get("name"),
            request.getQueryString().get("email")
        );
        return "200 OK".getBytes();
    }
}