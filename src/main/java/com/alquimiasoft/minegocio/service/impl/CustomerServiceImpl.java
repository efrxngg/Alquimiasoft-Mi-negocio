package com.alquimiasoft.minegocio.service.impl;

import com.alquimiasoft.minegocio.dto.CustomerCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerDTO;
import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.CustomerEntity;
import com.alquimiasoft.minegocio.repository.CustomerRepository;
import com.alquimiasoft.minegocio.service.CustomerService;
import com.alquimiasoft.minegocio.utils.Mapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

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
    public List<CustomerDTO> findAllCustomerstByIdentityNumberOrName(String identityNumber, String name) {
        return customerRepository.findAllByIdentificationNumberOrName(identityNumber, name)
                .stream().map(Mapper::toCustomerDTO).collect(Collectors.toList());
    }
}
