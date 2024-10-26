package webserver.entity;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class RequestEntityTest {
    
    @Test
    public void GET_방식으로_회원가입_할_수_있다() throws IOException {
        final String input = "GET /user/create?userId=userId&password=password&name=name&email=wlgns12370%gmail.com HTTP/1.1";
        InputStream in = new ByteArrayInputStream(input.getBytes());

        RequestEntity requestEntity = RequestEntity.from(in);

        assertEquals(HttpMethod.GET, requestEntity.getHttpMethod());
        assertEquals("/user/create", requestEntity.getUri());

        assertEquals("userId", requestEntity.getQueryString().get("userId"));
        assertEquals("password", requestEntity.getQueryString().get("password"));
        assertEquals("name", requestEntity.getQueryString().get("name"));
        assertEquals("wlgns12370%gmail.com", requestEntity.getQueryString().get("email"));
    }

    @Test
    public void POST_방식으로_회원가입_할_수_있다() throws IOException {
        String bodyData = "userId=userId&password=password&name=name&email=wlgns12370%gmail.com";
        
        String input = "POST /user/create HTTP/1.1\r\n" +
                    "content-length: " + bodyData.length() + "\r\n" +
                    "\r\n" +
                    bodyData;
                        
        InputStream in = new ByteArrayInputStream(input.getBytes());

        RequestEntity requestEntity = RequestEntity.from(in);

        assertEquals(HttpMethod.POST, requestEntity.getHttpMethod());
        assertEquals("/user/create", requestEntity.getUri());

        assertEquals("userId", requestEntity.getQueryString().get("userId"));
        assertEquals("password", requestEntity.getQueryString().get("password"));
        assertEquals("name", requestEntity.getQueryString().get("name"));
        assertEquals("wlgns12370%gmail.com", requestEntity.getQueryString().get("email"));
    }
}
