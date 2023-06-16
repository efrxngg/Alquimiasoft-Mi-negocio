package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.dto.AddressCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerCreateDTO;
import com.alquimiasoft.minegocio.dto.CustomerDTO;
import com.alquimiasoft.minegocio.dto.CustomerUpdateDTO;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
        CustomerDTO customerResult = getCustomerDTO();

        var customertUpdated = CustomerUpdateDTO.builder()
                .id(customerResult.getId())
                .identificationNumber("57495749574594")
                .identificationType(IdentificationType.RUC)
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
    }

    /**
     * Funcionalidad para registrar una nueva dirección por cliente
     */
    @Test
    void testSaveNewAddressForClient() {
        CustomerDTO customerResult = getCustomerDTO();
        log.info("Customer Result: {}", customerResult);

        var address2 = AddressCreateDTO.builder()
                .province("Guayas").city("Duran").address("M-x Sl-x")
                .build();

        var addressUpdate = customerService.saveNewAddressForCustomer(
                customerResult.getId(), address2).getAddresses();
        assertThat(addressUpdate).hasSize(2);
    }

    @Test
    void testRepeatedIdentifiedNumbers() {
        getCustomerDTO();
        assertThatThrownBy(this::getCustomerDTO).isInstanceOf(DataIntegrityViolationException.class);
    }

    private CustomerDTO getCustomerDTO() {
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
        return customerService.saveCustomerWithAddress(client);
    }
}
