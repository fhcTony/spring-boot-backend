package com.fhc.authenticationserver.config.oauth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhc.authenticationserver.model.ResultModel;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fuhongchao
 * 权限不足时的自定义返回格式
 */
@Component
public class RestfulAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        printWriter.write(objectMapper.writeValueAsString(ResultModel.forbidden(e.getMessage())));
        printWriter.flush();
        printWriter.close();
    }
}
