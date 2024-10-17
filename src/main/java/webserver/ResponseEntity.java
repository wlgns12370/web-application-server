package webserver;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ResponseEntity {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);

    private DataOutputStream dos;

    private ResponseEntity(DataOutputStream dos) {
        this.dos = dos;
    }

    public static ResponseEntity from(OutputStream out) {
        return new ResponseEntity(new DataOutputStream(out));
    }
    
    public ResponseEntity response200Header(int lengthOfBodyContent) {
        try {
            dos.writeBytes("HTTP/1.1 200 OK \r\n");
            dos.writeBytes("Content-Type: text/html;charset=utf-8\r\n");
            dos.writeBytes("Content-Length: " + lengthOfBodyContent + "\r\n");
            dos.writeBytes("\r\n");
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return this;
    }

    public ResponseEntity responseBody(byte[] body) {
        try {
            dos.write(body, 0, body.length);
            dos.flush();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return this;
    }
}
