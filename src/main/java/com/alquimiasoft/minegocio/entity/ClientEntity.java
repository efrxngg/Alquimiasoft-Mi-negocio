package com.alquimiasoft.minegocio.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "client_entity")
@AllArgsConstructor
@Data
@Builder
public class ClientEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @Enumerated(value = EnumType.STRING)
    private IdentificationType identificationType;

    private String identificationNumber;

    private String firstName;

    private String lastName;

    private String email;

    private String cellphone;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity mainAddress;

    @OneToMany
    private List<AddressEntity> addresses;

    public ClientEntity() {

    }

}
