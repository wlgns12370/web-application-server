package webserver.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class RenderingController implements Controller {

    @Override
    public ApiResult handleRequest(final RequestEntity request) {
        try {
            return ApiResult.from(HttpStatus.OK, Files.readAllBytes(new File("./webapp" + request.getUri()).toPath()), null);
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResult.from(HttpStatus.BAD_REQUEST, (e.getMessage() +"해당 경로의 파일을 랜더링 할 수 없습니다.").getBytes(), null);
        }
    }
}
