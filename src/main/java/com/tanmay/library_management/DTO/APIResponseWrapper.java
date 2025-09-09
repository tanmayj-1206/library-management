package com.tanmay.library_management.DTO;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class APIResponseWrapper<T> {
    private boolean isSuccess;
    private String message;
    private T payload;
    private String errorMessage;

    public APIResponseWrapper(boolean isSuccess, String message, T payload, String errorMessage) {
        this.isSuccess = isSuccess;
        this.message = message;
        this.payload = payload;
        this.errorMessage = errorMessage;
    }

    public static <T> APIResponseWrapper<T> success(String message, T payload) {
        return new APIResponseWrapper<>(true, message, payload, null);
    }

    public static <T> APIResponseWrapper<T> error(String message, String errorCode) {
        return new APIResponseWrapper<>(false, message, null, errorCode);
    }

}
