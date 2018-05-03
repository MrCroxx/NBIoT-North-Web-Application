package com.croxx.nbiot.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.constraints.NotNull;

public class ResMsg<T> {
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_DATA_REQUIRED = "data required";
    public static final String MSG_DATA_ILLEGAL = "data illegal";

    public static String getBindErrorsMessage(BindingResult bindingResult) {
        String msg = "";
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            msg += "[" + fieldError.getField() + "]" + fieldError.getDefaultMessage();
        }
        return msg;
    }


    private String msg;
    private T content;

    public ResMsg(@NotNull String msg) {
        this.msg = msg;
    }

    public ResMsg(@NotNull String msg, @NotNull T content) {
        this.msg = msg;
        this.content = content;
    }

    /*    Getters & Setters     */

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
