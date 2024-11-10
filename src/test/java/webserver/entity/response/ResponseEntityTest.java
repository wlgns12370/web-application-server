package webserver.entity.response;

import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import org.junit.Test;

import webserver.entity.ApiResult;

public class ResponseEntityTest {
    
    @Test
    public void 로그인한_사용자는_사용자_목록을_조회할_수_있다() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        ApiResult response = new ApiResult(HttpStatus.OK, "<th>사용자 아이디</th> <th>이름</th> <th>이메일</th>".getBytes(), true);
        
        ResponseEntity.from(out, response);

        String output = baos.toString();
        assertTrue(output.contains("<th>사용자 아이디</th>"));
        assertTrue(output.contains("<th>이름</th>"));
        assertTrue(output.contains("<th>이메일</th>"));
    }

    @Test
    public void 로그인하지_않은_사용자는_사용자_목록을_조회할_수_없다() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(baos);
        ApiResult response = new ApiResult(HttpStatus.UNAUTHORIZED, null, false);
        
        ResponseEntity.from(out, response);

        String output = baos.toString();
        assertTrue(output.contains("Set-Cookie: logined=false"));
    }
}
