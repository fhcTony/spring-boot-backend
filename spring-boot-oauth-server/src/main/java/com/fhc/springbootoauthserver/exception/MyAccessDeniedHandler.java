package com.fhc.springbootoauthserver.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuhongchao
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AccessDeniedException e) throws IOException, ServletException {
        httpResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        httpResponse.setContentType("application/json;charset=UTF-8");
        PrintWriter out=httpResponse.getWriter();
        Map<String,Object> map=new HashMap<String, Object>();
        map.put("code",HttpServletResponse.SC_FORBIDDEN);
        map.put("message","权限不足!");
        ObjectMapper objectMapper=new ObjectMapper();
        out.write(objectMapper.writeValueAsString(map));
        out.flush();
        out.close();
    }
}
