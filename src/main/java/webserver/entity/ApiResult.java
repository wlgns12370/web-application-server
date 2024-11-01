package webserver.entity;

import webserver.entity.response.HttpStatus;

public class ApiResult {
    private final HttpStatus httpStatus;
    private final byte[] body;
    private final Boolean isLogined;

    public ApiResult(final HttpStatus httpStatus,final byte[] body, final Boolean isLogined) {
        this.httpStatus = httpStatus;
        this.body = body;
        this.isLogined = isLogined;
    }

    public static ApiResult from(final HttpStatus httpStatus, final byte[] body, final Boolean isLogined) {
        return new ApiResult(httpStatus, body, isLogined);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public byte[] getBody() {
        return body;
    }

    public Boolean isLogined() {
        return isLogined;
    }
}
