package webserver;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            RequestEntity request = RequestEntity.from(in);
            
            byte[] body = Files.readAllBytes(new File("./webapp" + request.getHttpMessage()).toPath());
            
            ResponseEntity.from(out)
                .response200Header(body.length)
                .responseBody(body);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
