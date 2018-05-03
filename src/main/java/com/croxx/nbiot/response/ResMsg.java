package com.croxx.nbiot.response;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResMsg<T> {
    public static final String MSG_SUCCESS = "success";
    public static final String MSG_DATA_REQUIRED = "data required";
    public static final String MSG_DATA_ILLEGAL = "data illegal";

    public static List<String> getBindErrorsMessage(BindingResult bindingResult) {
        List<String> msgs = new ArrayList<>();
        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            msgs.add(fieldError.getField() + ":" + fieldError.getDefaultMessage());
        }
        return msgs;
    }


    private List<String> msgs;
    private T content;

    public ResMsg(@NotNull String... msgs) {
        this.msgs = new ArrayList<>();
        this.msgs.addAll(Arrays.asList(msgs));
    }

    public ResMsg(@NotNull List<String> msgs) {
        this.msgs = new ArrayList<>();
        this.msgs.addAll(msgs);
    }

    public ResMsg(@NotNull T content, @NotNull String... msgs) {
        this.msgs = new ArrayList<>();
        this.msgs.addAll(Arrays.asList(msgs));
        this.content = content;
    }

    public ResMsg(@NotNull T content, @NotNull List<String> msgs) {
        this.msgs = new ArrayList<>();
        this.msgs.addAll(msgs);
        this.content = content;
    }

    /*    Getters & Setters     */

    public List<String> getMsgs() {
        return msgs;
    }

    public void setMsgs(List<String> msgs) {
        this.msgs = msgs;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
