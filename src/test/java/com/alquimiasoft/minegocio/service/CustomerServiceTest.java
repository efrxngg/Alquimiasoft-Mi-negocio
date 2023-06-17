package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.*;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Log4j2
@ActiveProfiles("dev")
@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CustomerServiceTest {
    @Autowired
    private CustomerService customerService;

    /**
     * Funcionalidad para editar los datos del cliente
     */
    @Test
    void testUpdateOnlyCustomerData() {
        var address = AddressCreateDTO.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        var client = CustomerCreateDTO.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .address(address)
                .build();
        CustomerDTO customerResult = customerService.saveCustomerWithAddress(client);

        var customertUpdated = CustomerUpdateDTO.builder()
                .id(customerResult.getId())
                .identificationNumber("57495749574594")
                .identificationType("RUC")
                .firstName("Daniel")
                .lastName("Chavez")
                .email("daniel@test.com")
                .cellphone("0997188088")
                .build();
        CustomerDTO clientUpateResult = customerService.updateCustomerInformationOnly(customertUpdated);

        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("names", "Daniel Chavez");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("email", "daniel@test.com");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("cellphone", "0997188088");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("identificationType", "RUC");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("identificationNumber", "57495749574594");
        customerService.deleteCustomerById(customerResult.getId());
    }

    /**
     * Funcionalidad para registrar una nueva dirección por cliente
     */
    @Test
    void testSaveNewAddressForClient() {
        var address = AddressCreateDTO.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        var client = CustomerCreateDTO.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("09549431223")
                .address(address)
                .build();
        CustomerDTO customerResult = customerService.saveCustomerWithAddress(client);
        log.info("Customer Result: {}", customerResult);

        var address2 = AddressCreateDTO.builder()
                .province("Guayas").city("Duran").address("M-x Sl-x")
                .build();

        customerService.saveNewAddressForCustomer(
                customerResult.getId(), address2);

        List<AddressUpdateDTO> addresses = customerService.findAllAddressByCustomerId(customerResult.getId());
        assertThat(addresses).hasSize(2);
        customerService.deleteCustomerById(customerResult.getId());
    }

    @Test
    void testRepeatedIdentifiedNumbers() {
        var address = AddressCreateDTO.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        var customerCreateDTO = CustomerCreateDTO.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .address(address)
                .build();
        var customerDTO = customerService.saveCustomerWithAddress(customerCreateDTO);
        assertThatThrownBy(() -> customerService.saveCustomerWithAddress(customerCreateDTO))
                .isInstanceOf(DataIntegrityViolationException.class);
        customerService.deleteCustomerById(customerDTO.getId());
    }
}
