package webserver.controller;

import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;

public interface Controller {
    ApiResult handleRequest(RequestEntity request);
}