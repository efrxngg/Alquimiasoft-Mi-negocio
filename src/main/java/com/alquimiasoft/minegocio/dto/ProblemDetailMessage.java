package com.alquimiasoft.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.net.URI;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProblemDetailMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private String message;
    private URI path;
    private LocalDateTime date;
}
