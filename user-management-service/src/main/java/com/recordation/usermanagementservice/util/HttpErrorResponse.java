package com.recordation.usermanagementservice.util;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpErrorResponse {

    @JsonProperty("error_code")
    private String errorCode;
    private Object message;

}
