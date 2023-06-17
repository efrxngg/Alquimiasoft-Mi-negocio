package com.alquimiasoft.minegocio.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El ID no puede ser nulo")
    @NotEmpty(message = "El ID no puede estar vacío")
    private String id;

    @NotNull(message = "El tipo de identificación no puede ser nulo")
    @NotEmpty(message = "El tipo de identificación no puede estar vacío")
    private String identificationType;

    @NotNull(message = "El número de identificación no puede ser nulo")
    @NotEmpty(message = "El número de identificación no puede estar vacío")
    private String identificationNumber;

    @NotNull(message = "Los nombres no pueden ser nulos")
    @NotEmpty(message = "Los nombres no pueden estar vacíos")
    private String names;

    @Email(message = "El correo electrónico no es válido")
    private String email;

    @Pattern(regexp = "^(\\+)?[0-9]{1,3}[0-9]{9,10}$", message = "El número de teléfono no es válido")
    private String cellphone;

    private String mainProvince;
    private String mainCity;
    private String mainAddress;
}
