package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerEntity> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name);
}
