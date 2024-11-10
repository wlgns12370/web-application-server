package webserver.controller;

import java.util.Collection;

import db.DataBase;
import model.User;
import webserver.entity.ApiResult;
import webserver.entity.request.RequestEntity;
import webserver.entity.response.HttpStatus;

public class UserListController implements Controller {

    @Override
    public ApiResult handleRequest(RequestEntity request) {
        if (request.isLogined()) {
            Collection<User> users = DataBase.findAll();
            StringBuilder htmlTable = usersToHtmlTable(users);
            return ApiResult.from(HttpStatus.OK, htmlTable.toString().getBytes(), request.isLogined());    
        }
        return ApiResult.from(HttpStatus.UNAUTHORIZED, null, request.isLogined());
    }

    private StringBuilder usersToHtmlTable(final Collection<User> users) {
        StringBuilder htmlTable = new StringBuilder();
        htmlTable.append("<table class=\"table table-hover\">\n");
        htmlTable.append("    <thead>\n");
        htmlTable.append("        <tr>\n");
        htmlTable.append("            <th>#</th>\n");
        htmlTable.append("            <th>사용자 아이디</th>\n");
        htmlTable.append("            <th>이름</th>\n");
        htmlTable.append("            <th>이메일</th>\n");
        htmlTable.append("            <th></th>\n");
        htmlTable.append("        </tr>\n");
        htmlTable.append("    </thead>\n");
        htmlTable.append("    <tbody>\n");
        int i = 0;
        for (User user : users) {
            htmlTable.append("        <tr>\n");
            htmlTable.append("            <th scope=\"row\">" + (i + 1) + "</th>\n");
            htmlTable.append("            <td>" + user.getUserId() + "</td>\n");
            htmlTable.append("            <td>" + user.getName() + "</td>\n");
            htmlTable.append("            <td>" + user.getEmail() + "</td>\n");
            htmlTable.append("        </tr>\n");
        }
        htmlTable.append("    </tbody>\n");
        htmlTable.append("</table>");
        return htmlTable;
    }
}
