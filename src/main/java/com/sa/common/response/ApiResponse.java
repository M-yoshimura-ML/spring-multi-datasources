package com.sa.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T result;
    private List<String> errors;
    private ApiParamsResponse params;

    /**
     * @param success - checks if api call is successful
     * @param message - set response message
     * @param result  - set response result
     */
    public ApiResponse(boolean success, String message, T result, List<String> errors) {
        super();
        this.success = success;
        this.message = message;
        this.result = result;
        this.errors = errors;
    }

    /**
     * @param success - checks if api call is successful
     * @param message - set response message
     * @param result  - set response result
     */
    public ApiResponse(boolean success, String message, T result) {
        super();
        this.success = success;
        this.message = message;
        this.result = result;
    }

    /**
     * @param success - checks if api call is successful
     * @param message - set response message
     */
    public ApiResponse(boolean success, String message) {
        super();
        this.success = success;
        this.message = message;
    }
}
