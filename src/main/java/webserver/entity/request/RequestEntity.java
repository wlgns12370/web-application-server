package webserver.entity.request;

import static util.HttpRequestUtils.parseQueryString;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import util.HttpRequestUtils;
import util.IOUtils;
import util.HttpRequestUtils.Pair;

public class RequestEntity {
    private final HttpMethod httpMethod;
    private final String uri;
    private final Map<String, String> queryString;
    private final Map<String, String> headers;

    public RequestEntity(
        final HttpMethod httpMethod,
        final String uri,
        final Map<String, String> queryString,
        final Map<String, String> headers
    ) {
        this.httpMethod = httpMethod;
        this.uri = uri;
        this.queryString = queryString;
        this.headers = headers;
    }

    public static RequestEntity from(InputStream in) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String[] line = br.readLine().split(" ");
        int index = line[1].indexOf("?");

        List<String> headers = new ArrayList<>();
        String inputLine;

        while (true) {
            inputLine = br.readLine();
            if (inputLine == null || inputLine.equals("")) {
                break;
            }
            headers.add(inputLine);
        }

        Map<String, String> headerMap = parseHeader(headers);
        Map<String, String> queryString = null;

        if (HttpMethod.GET.toString().equals(line[0])) {
            if (hasQueryString(index)) {
                String requestPath = line[1].substring(0, index);
                String param = line[1].substring(index+1);
                queryString = parseQueryString(param);
                return new RequestEntity(HttpMethod.valueOf(line[0]), requestPath, queryString, headerMap);
            }
        }
        if (HttpMethod.POST.toString().equals(line[0])) {
            String body = parseBody(br, Integer.parseInt(headerMap.get("content-length")));
            queryString = parseQueryString(body);
        }
        return new RequestEntity(HttpMethod.valueOf(line[0]), line[1], queryString, headerMap);
    }

    private static boolean hasQueryString(int index) {
        if (index != -1) {
            return true;
        }
        return false;
    }

    private static Map<String, String> parseHeader(List<String> headers) {
        Map<String, String> headerMap = new HashMap<>();
        headers.forEach(header -> {
            Pair pair = HttpRequestUtils.parseHeader(header);
            headerMap.put(pair.getKey(),pair.getValue());
        });
        return headerMap;
    }

    private static String parseBody(BufferedReader br, int contentLength) throws IOException{
        return IOUtils.readData(br, contentLength);
    }

    public String getUri() {
        return uri;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public Map<String, String> getQueryString() {
        return queryString;
    }
}
