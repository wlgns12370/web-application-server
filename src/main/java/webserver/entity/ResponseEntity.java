package webserver.entity;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webserver.RequestHandler;

public class ResponseEntity {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    public static void from(OutputStream out, ApiResult response) {
        DataOutputStream dos = new DataOutputStream(out);
        try {
            if (response.getHttpStatus().is2xx()) {
                response200Header(dos, response.getBody().length);    
                responseBody(dos, response.getBody());
            }
            if (response.getHttpStatus().is3xx()) {
                response302Header(dos);
            }
            if (response.getHttpStatus().is4xx()) {
                
            }
            if (response.getHttpStatus().is5xx()) {
                
            }
            dos.flush();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
    
    private static void response200Header(DataOutputStream dos, int lengthOfBodyContent) throws IOException {
        dos.writeBytes("HTTP/1.1 200 OK \r\n");
        dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
        dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
        dos.writeBytes("\r\n");
    }

    private static void response302Header(DataOutputStream dos) throws IOException {
        dos.writeBytes("HTTP/1.1 302 Found \r\n");
        dos.writeBytes("Location: https://h.principes.xyz/code/keephun/proxy/8080/index.html\r\n");
        dos.writeBytes("\r\n");
    }

    private static void responseBody(DataOutputStream dos, byte[] body) throws IOException {
        dos.write(body, 0, body.length);
    }
}