package webserver.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import webserver.entity.RequestEntity;

public class RenderingController implements Controller {

    @Override
    public byte[] handleRequest(final RequestEntity request) {
        try {
            return Files.readAllBytes(new File("./webapp" + request.getUri()).toPath());
        } catch (IOException e) {
            e.printStackTrace();
            return (e.getMessage() +"해당 경로의 파일을 랜더링 할 수 없습니다.").getBytes();
        }
    }
}
