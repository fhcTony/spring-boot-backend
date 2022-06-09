package com.fhc.authenticationserver.config.oauth.component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fhc.authenticationserver.model.ResultModel;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author fuhongchao
 * @create 2020/5/18 13:20
 */
@Component
public class RestfulAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter printWriter = response.getWriter();
        ObjectMapper objectMapper = new ObjectMapper();
        printWriter.write(objectMapper.writeValueAsString(ResultModel.unauthorized(e.getMessage())));
        printWriter.flush();
        printWriter.close();
    }
}
