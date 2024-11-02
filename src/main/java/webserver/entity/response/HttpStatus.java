package webserver.entity.response;

public enum HttpStatus {
    OK(200, "OK"),
    CREATED(201, "Created"),
    NO_CONTENT(204, "No Content"),
    FOUND(302,"Found"),
    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");
    
    private final int value;
    private final String reasonPhrase;
    
    private HttpStatus(int value, String reasonPhrase) {
        this.value = value;
        this.reasonPhrase = reasonPhrase;
    }

    public int getValue() {
        return value;
    }

    public String getReasonPhrase() {
        return reasonPhrase;
    }

    public boolean isSame(HttpStatus other) {
        if (other == null) {
            return false;
        }
        return this.value == other.value;
    }

    public boolean is2xx() {
        return this.value >= 200 && this.value < 300;
    }

    public boolean is3xx() {
        return this.value >= 300 && this.value < 400;
    }

    public boolean is4xx() {
        return this.value >= 400 && this.value < 500;
    }

    public boolean is5xx() {
        return this.value >= 500 && this.value < 600;
    }
}