package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.dto.*;
import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.CustomerEntity;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import com.alquimiasoft.minegocio.repository.AddressRepository;
import com.alquimiasoft.minegocio.repository.CustomerRepository;
import com.alquimiasoft.minegocio.service.CustomerService;
import com.alquimiasoft.minegocio.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    @Override
    @Transactional
    public CustomerDTO saveCustomerWithAddress(CustomerCreateDTO customer) {
        CustomerEntity customerEntity = Mapper.toCustomerEntity(customer);
        Optional<AddressEntity> optionalAddressEntity = customerEntity.getAddresses()
                .stream()
                .findFirst();
        customerEntity.setMainAddress(optionalAddressEntity.orElse(null));

        return Mapper.toCustomerDTO(customerRepository.save(customerEntity));
    }

    @Override
    public CustomerDTO updateCustomerInformationOnly(CustomerUpdateDTO customerUpdateDTO) {
        CustomerEntity customerEntity = customerRepository.findById(UUID.fromString(customerUpdateDTO.getId())).orElseThrow();
        customerEntity.setIdentificationType(IdentificationType.fromString(customerUpdateDTO.getIdentificationType()));
        customerEntity.setIdentificationNumber(customerUpdateDTO.getIdentificationNumber());
        customerEntity.setFirstName(customerUpdateDTO.getFirstName());
        customerEntity.setLastName(customerUpdateDTO.getLastName());
        customerEntity.setEmail(customerUpdateDTO.getEmail());
        customerEntity.setCellphone(customerUpdateDTO.getCellphone());
        return Mapper.toCustomerDTO(customerRepository.save(customerEntity));
    }

    @Override
    public List<CustomerDTO> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name) {
        return customerRepository.findAllByIdentificationNumberOrName(identityNumber, name)
                .stream().map(Mapper::toCustomerDTO).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AddressUpdateDTO saveNewAddressForCustomer(String id, AddressCreateDTO address) {
        Optional<CustomerEntity> customerOptional = customerRepository.findById(UUID.fromString(id));
        CustomerEntity customerEntity = customerOptional.orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        List<AddressEntity> addresses = new ArrayList<>(customerEntity.getAddresses());
        AddressEntity newAddress = addressRepository.save(Mapper.toAddressEntity(address));
        addresses.add(newAddress);

        customerEntity.setAddresses(addresses);
        customerRepository.save(customerEntity);
        return Mapper.toAddressUpdateDTO(newAddress);
    }

    @Override
    public boolean deleteCustomerById(String id) {
        UUID uuid = UUID.fromString(id);
        boolean exists = customerRepository.existsById(uuid);
        if (exists) {
            customerRepository.deleteById(uuid);
        }
        return exists;
    }

    @Override
    @Transactional
    public List<AddressUpdateDTO> findAllAddressByCustomerId(String id) {
        List<AddressEntity> addresses = customerRepository.findById(UUID.fromString(id))
                .map(CustomerEntity::getAddresses).orElse(Collections.emptyList());

        return addresses.stream()
                .map(Mapper::toAddressUpdateDTO)
                .collect(Collectors.toList());
    }
}
