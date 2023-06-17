package com.alquimiasoft.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@NotEmpty
@NotEmpty
public class AddressUpdateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;
    private String province;
    private String city;
    private String address;
}
