package com.fhc.authenticationserver.common.exception.oauth;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @author fuhongchao
 * @create 2020/5/18 13:20
 */
public class AuthExceptionEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest httpRequest, HttpServletResponse httpResponse, AuthenticationException authException) throws ServletException {
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json");
        Map<String,Object> map = new HashMap<>(16);
        map.put("code", HttpServletResponse.SC_UNAUTHORIZED);
        map.put("message", authException.getMessage());
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(httpResponse.getOutputStream(), map);
        } catch (Exception e) {
            throw new ServletException();
        }
    }
}
