package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.entity.ClientEntity;
import com.alquimiasoft.minegocio.repository.IClientRepository;
import com.alquimiasoft.minegocio.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements ClientService {

    private final IClientRepository clientRepository;

    @Override
    public List<ClientEntity> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name) {
        return clientRepository.findAllByIdentificationNumberOrName(identityNumber, name);
    }
}
