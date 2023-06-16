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
public class AddressCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String province;
    private String city;
    private String address;
}
