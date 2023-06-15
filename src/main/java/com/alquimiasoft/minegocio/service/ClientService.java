package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.entity.ClientEntity;

import java.util.List;

public interface ClientService {
    List<ClientEntity> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name);
}
