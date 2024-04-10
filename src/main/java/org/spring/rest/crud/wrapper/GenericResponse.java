package org.spring.rest.crud.wrapper;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GenericResponse {

    @JsonProperty("message")
    private String statusMsg;
    @JsonProperty("type")
    private String statusType;
    @JsonProperty("code")
    private Integer statusCode;
    @JsonProperty("timestamp")
    private LocalDateTime localDateTime;
    @JsonProperty("description")
    private String description;

}
