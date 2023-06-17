package com.alquimiasoft.minegocio.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "address_entity")
@AllArgsConstructor
@Data
@Builder
public class AddressEntity {
    @Id
    @GeneratedValue
    private UUID id;

    private String province;

    private String city;

    private String address;
    
    public AddressEntity() {

    }

}
