package com.alquimiasoft.minegocio.dto;

import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerCreateDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "El nombre no puede ser nulo")
    @NotEmpty(message = "El nombre no puede estar vacío")
    private String firstName;

    @NotNull(message = "El apellido no puede ser nulo")
    @NotEmpty(message = "El apellido no puede estar vacío")
    private String lastName;

    @NotNull(message = "El tipo de identificación no puede ser nulo")
    private IdentificationType identificationType;

    @NotNull(message = "El número de identificación no puede ser nulo")
    @NotEmpty(message = "El número de identificación no puede estar vacío")
    private String identificationNumber;

    @Email(message = "El correo electrónico no es válido")
    private String email;

    @Pattern(regexp = "^(\\+)?[0-9]{1,3}[0-9]{9,10}$", message = "El número de teléfono no es válido")
    private String cellphone;

    @Valid
    private AddressCreateDTO address;
}
