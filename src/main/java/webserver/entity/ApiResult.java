package webserver.entity;

public class ApiResult {
    private final HttpStatus httpStatus;
    private final byte[] body;

    private ApiResult(HttpStatus httpStatus, byte[] body) {
        this.httpStatus = httpStatus;
        this.body = body;
    }

    public static ApiResult from(final HttpStatus httpStatus, final byte[] body) {
        return new ApiResult(httpStatus, body);
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public byte[] getBody() {
        return body;
    }
}
