package org.spring.rest.crud.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.spring.rest.crud.wrapper.GenericResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
      throws IOException {

    response.setContentType("application/json");
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    OutputStream outputStream = response.getOutputStream();

    GenericResponse apiError = GenericResponse.builder().statusMsg("Unauthorized")
        .statusCode(HttpStatus.UNAUTHORIZED.value()).statusType(HttpStatus.UNAUTHORIZED.getReasonPhrase()).build();
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.writeValue(outputStream, apiError);
    outputStream.flush();


    response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized : Server");

  }
}
