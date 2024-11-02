package webserver.fixture;

import java.util.HashMap;
import java.util.Map;

public class RequestEntityFixture {
    private static final Map<String, String> queryString = new HashMap<String, String>() {{
        put("password", "password");
        put("name", "name");
        put("userId", "user");
        put("email", "ss@gmail.com");
    }};

    private static final Map<String, String> invalidQueryString = new HashMap<String, String>() {{
        put("password", "invalidPassword");
        put("name", "name");
        put("userId", "user");
        put("email", "ss@gmail.com");
    }};
    
    private static final Map<String, String> headers = new HashMap<String, String>() {{
        put("sec-fetch-mode", "navigate");
        put("referer", "http:example.com");
        put("content-length", "60");
        put("sec-fetch-site", "same-origin");
        put("cookie", "logined=true;");
        put("accept-language", "ko-KR,ko;q=0.9,en-US;q=0.8,en;q=0.7");
        put("origin", "");
        put("x-forwarded-for", "121.151.1.45");
        put("sec-fetch-user", "?1");
        put("accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.7");
        put("sec-ch-ua", "\"Not.A/Brand\";v=\"8\", \"Chromium\";v=\"114\", \"Google Chrome\";v=\"114\"");
        put("sec-ch-ua-mobile", "?0");
        put("sec-ch-ua-platform", "\"Windows\"");
        put("upgrade-insecure-requests", "1");
        put("host", "example.com");
        put("content-type", "application/x-www-form-urlencoded");
        put("connection", "upgrade");
        put("cache-control", "max-age=0");
        put("accept-encoding", "gzip");
        put("sec-fetch-dest", "document");
        put("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/114.0.0.0 Safari/537.36");
    }};

    public static Map<String, String> queryString() {
        return queryString;
    }

    public static Map<String, String> invalidQueryString() {
        return invalidQueryString;
    }

    public static Map<String, String> headers() {
        return headers;
    }
}