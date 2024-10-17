package webserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestEntity {
    private InputStream in;

    private RequestEntity(InputStream in) throws IOException {
        this.in = in;
    }

    public static RequestEntity from(InputStream in) throws IOException {
        return new RequestEntity(in);
    }

    public String getHttpMessage() throws IOException {
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader input = new BufferedReader(isr);
        String line = input.readLine();
        
        return line.split(" ")[1];
    }
}
