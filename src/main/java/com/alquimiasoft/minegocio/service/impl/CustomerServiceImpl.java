package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.entity.CustomerEntity;
import com.alquimiasoft.minegocio.repository.CustomerRepository;
import com.alquimiasoft.minegocio.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository clientRepository;

    @Override
    public List<CustomerEntity> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name) {
        return clientRepository.findAllByIdentificationNumberOrName(identityNumber, name);
    }
}
