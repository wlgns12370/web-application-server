package webserver.controller;

import static org.junit.Assert.*;
import static webserver.fixture.RequestEntityFixture.headers;
import static webserver.fixture.RequestEntityFixture.invalidQueryString;
import static webserver.fixture.RequestEntityFixture.queryString;

import org.junit.Before;
import org.junit.Test;

import db.DataBase;
import model.User;
import webserver.entity.ApiResult;
import webserver.entity.request.HttpMethod;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class LoginControllerTest {
    private LoginController loginController;
    private final String userId = "user";

    @Before
    public void setUp() {
        loginController = new LoginController();
        DataBase.addUser(new User(userId, "password", "name", "kk@gmail.com"));
    }

    @Test
    public void 사용자는_로그인에_성공_한다() {
        final RequestEntity actual = new RequestEntity(
            HttpMethod.POST,
            "/user/create",
            queryString(),
            headers()
        );

        final ApiResult result = loginController.handleRequest(actual);
        final ApiResult expected = new ApiResult(HttpStatus.OK, null, true);

        assertEquals(expected.getHttpStatus(), result.getHttpStatus());
        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.isLogined(), result.isLogined());
    }

    @Test
    public void 사용자는_로그인에_실패_한다() {
        final RequestEntity actual = new RequestEntity(
            HttpMethod.POST,
            "/user/create",
            invalidQueryString(),
            headers()
        );

        final ApiResult result = loginController.handleRequest(actual);
        final ApiResult expected = new ApiResult(HttpStatus.UNAUTHORIZED, null, false);

        assertEquals(expected.getHttpStatus(), result.getHttpStatus());
        assertEquals(expected.getBody(), result.getBody());
        assertEquals(expected.isLogined(), result.isLogined());
    }
}
