package webserver.controller;

import webserver.entity.RequestEntity;

public interface Controller {
    byte[] handleRequest(RequestEntity request);
}