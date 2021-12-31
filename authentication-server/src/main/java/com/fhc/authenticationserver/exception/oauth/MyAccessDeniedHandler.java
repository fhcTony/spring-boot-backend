package com.fhc.authenticationserver.exception.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhc.authenticationserver.common.Status;
import com.fhc.authenticationserver.model.ResultModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fuhongchao
 * 权限不足时的自定义返回格式
 */
@Component
public class MyAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AccessDeniedException e) throws IOException, ServletException {
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json");
        PrintWriter out = httpResponse.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        out.write(objectMapper.writeValueAsString(ResultModel.ofStatus(Status.ACCESS_DENIED)));
        out.flush();
        out.close();
    }
}