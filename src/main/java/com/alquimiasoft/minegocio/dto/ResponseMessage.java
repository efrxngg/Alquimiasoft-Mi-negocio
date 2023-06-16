package com.alquimiasoft.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private T content;

    public ResponseMessage(String message) {
        this.message = message;
    }
}
