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
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String identificationType;
    private String identificationNumber;
    private String names;
    private String email;
    private String cellphone;
    private String mainProvince;
    private String mainCity;
    private String mainAddress;

}
