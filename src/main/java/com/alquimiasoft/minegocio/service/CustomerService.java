package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.*;
import com.alquimiasoft.minegocio.entity.CustomerEntity;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name);

    CustomerDTO saveCustomerWithAddress(CustomerCreateDTO customerCreateDTO);

    CustomerDTO updateCustomerInformationOnly(CustomerUpdateDTO customerUpdateDTO);

    boolean deleteCustomerById(String id);

    CustomerEntity saveNewAddressForCustomer(String id, AddressCreateDTO address);

    List<AddressUpdateDTO> findAllAddressByCustomerId(String id);
}
