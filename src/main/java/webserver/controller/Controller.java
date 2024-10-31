package webserver.controller;

import webserver.entity.ApiResult;
import webserver.entity.RequestEntity;

public interface Controller {
    ApiResult handleRequest(RequestEntity request);
}