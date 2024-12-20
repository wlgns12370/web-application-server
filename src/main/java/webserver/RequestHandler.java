package webserver;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.ResponseEntity;

public class RequestHandler extends Thread {
    private static final Logger log = LoggerFactory.getLogger(RequestHandler.class);
    private final ControllerDispatcher dispatcher = new ControllerDispatcher();
    private Socket connection;

    public RequestHandler(Socket connectionSocket) {
        this.connection = connectionSocket;
    }

    public void run() {
        log.debug("New Client Connect! Connected IP : {}, Port : {}", connection.getInetAddress(),
                connection.getPort());

        try (InputStream in = connection.getInputStream(); OutputStream out = connection.getOutputStream()) {
            RequestEntity request = RequestEntity.from(in);
            
            ApiResult response = dispatcher.dispatch(request);

            ResponseEntity.from(out, response);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}