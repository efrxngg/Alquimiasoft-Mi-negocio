package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.CustomerCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    CustomerDTO saveCustomerWithAddress(CustomerCreateDTO customerCreateDTO);

    List<CustomerDTO> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name);
}
