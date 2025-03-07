package eci.edu.co.http;

import java.util.Map;

public class Request {
    private String method;
    private String path;
    private Map<String, String> headers;
    private String body;

    public Request(String method, String path, Map<String, String> headers, String body) {
        this.method = method;
        this.path = path;
        this.headers = headers;
        this.body = body;
    }

    public String getMethod() {
        return method;
    }
    public String getPath() {
        return path;
    }
    public Map<String, String> getHeaders() {
        return headers;
    }
    public String getBody() {
        return body;
    }
}
