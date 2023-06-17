package com.alquimiasoft.minegocio.entity;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "customer_entity")
@AllArgsConstructor
@Data
@Builder
public class CustomerEntity {
    @Id
    @GeneratedValue
    private UUID id;

    @NotNull
    @Enumerated(value = EnumType.STRING)
    private IdentificationType identificationType;

    @Column(unique = true)
    @NotNull
    private String identificationNumber;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String cellphone;

    private String email;

    @ManyToOne(cascade = CascadeType.ALL)
    private AddressEntity mainAddress;

    @OneToMany
    private List<AddressEntity> addresses;

    public CustomerEntity() {

    }

}
