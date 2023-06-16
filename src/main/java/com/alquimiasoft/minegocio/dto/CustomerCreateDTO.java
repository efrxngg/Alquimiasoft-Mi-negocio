package com.alquimiasoft.minegocio.dto;

import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private IdentificationType identificationType;
    private String identificationNumber;
    private String email;
    private String cellphone;
    private AddressCreateDTO address;
}
