package com.alquimiasoft.minegocio.utils;

import com.alquimiasoft.minegocio.dto.AddressCreateDTO;
import com.alquimiasoft.minegocio.dto.AddressUpdateDTO;
import com.alquimiasoft.minegocio.dto.CustomerCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerDTO;
import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.CustomerEntity;

import java.util.List;

public class Mapper {
    public static CustomerDTO toCustomerDTO(CustomerEntity customerEntity) {
        return CustomerDTO.builder()
                .id(customerEntity.getId().toString())
                .names(String.format("%s %s", customerEntity.getFirstName(), customerEntity.getLastName()))
                .identificationNumber(customerEntity.getIdentificationNumber())
                .identificationType(customerEntity.getIdentificationType().toString())
                .cellphone(customerEntity.getCellphone())
                .email(customerEntity.getEmail())
                .mainProvince(customerEntity.getMainAddress().getProvince())
                .mainCity(customerEntity.getMainAddress().getCity())
                .mainAddress(customerEntity.getMainAddress().getAddress())
                .build();
    }

    public static CustomerEntity toCustomerEntity(CustomerCreateDTO entity) {
        return CustomerEntity.builder()
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .identificationType(entity.getIdentificationType())
                .identificationNumber(entity.getIdentificationNumber())
                .cellphone(entity.getCellphone())
                .email(entity.getEmail())
                .addresses(List.of(AddressEntity.builder()
                        .province(entity.getAddress().getProvince())
                        .city(entity.getAddress().getCity())
                        .address(entity.getAddress().getAddress())
                        .build()))
                .build();
    }

    public static AddressEntity toAddressEntity(AddressCreateDTO address) {
        return AddressEntity.builder()
                .province(address.getProvince())
                .city(address.getCity())
                .address(address.getAddress())
                .build();
    }

    public static AddressUpdateDTO toAddressUpdateDTO(AddressEntity addressEntity) {
        return AddressUpdateDTO.builder()
                .id(addressEntity.getId().toString())
                .province(addressEntity.getProvince())
                .city(addressEntity.getCity())
                .address(addressEntity.getAddress())
                .build();
    }
}
