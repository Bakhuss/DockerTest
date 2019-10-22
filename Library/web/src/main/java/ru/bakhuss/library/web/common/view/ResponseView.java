package ru.bakhuss.library.web.common.view;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class ResponseView {

    public String result;
    public Object data;


    public ResponseView() {

    }

    public ResponseView(boolean result) {
        if (result) this.result = "success";
    }

    public ResponseView(Object data) {
        this.data = data;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "{result:" + result +
                ";data:" + data +
                "}";
    }
}
