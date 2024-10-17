package webserver.entity;

import static util.HttpRequestUtils.parseQueryString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class RequestEntity {
    private final HttpMethod httpMessage;
    private final String uri;
    private final Map<String, String> queryString;

    private RequestEntity(final HttpMethod httpMessage, final String uri) throws IOException {
        this.httpMessage = httpMessage;
        this.uri = uri;
        this.queryString = null;
    }

    private RequestEntity(final HttpMethod httpMessage, final String uri, final Map<String, String> queryString) throws IOException {
        this.httpMessage = httpMessage;
        this.uri = uri;
        this.queryString = queryString;
    }

    private static boolean hasQueryString(int index) {
        if (index != -1) {
            return true;
        }
        return false;
    }

    public static RequestEntity from(InputStream in) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(in));
        String[] line = input.readLine().split(" ");
        int index = line[1].indexOf("?");
        if (hasQueryString(index)) {
            String requestPath = line[1].substring(0, index);
            String param = line[1].substring(index+1);
            Map<String, String> queryString = parseQueryString(param);

            return new RequestEntity(HttpMethod.valueOf(line[0]), requestPath, queryString);
        }
        return new RequestEntity(HttpMethod.valueOf(line[0]), line[1]);
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMessage() {
        return httpMessage;
    }

    public Map<String, String> getQueryString() {
        return queryString;
    }
}
