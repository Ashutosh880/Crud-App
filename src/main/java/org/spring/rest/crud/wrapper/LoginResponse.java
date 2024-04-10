package org.spring.rest.crud.wrapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
	private String jwtToken;
	private String username;
	private String status;
	private String message;
}
