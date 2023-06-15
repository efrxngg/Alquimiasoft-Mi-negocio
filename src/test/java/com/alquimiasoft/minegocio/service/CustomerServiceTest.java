package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.CustomerEntity;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@Log4j2
@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerServiceTest {
    /**
     * Funcionalidad para editar los datos del cliente
     */
    @Test
    void testUpdateOnlyCustomerData() {
        var address = AddressEntity.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        var client = CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .mainAddress(address)
                .build();
        CustomerEntity clientResult = CustomerEntity.builder().build();

        var clientUpdate = CustomerEntity.builder()
                .id(clientResult.getId())
                .identificationNumber("57495749574594")
                .identificationType(IdentificationType.RUC)
                .firstName("Daniel")
                .lastName("Chavez")
                .email("daniel@test.com")
                .cellphone("0997188088")
                .build();
        CustomerEntity clientUpateResult = CustomerEntity.builder().build();

        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("firstName", "Daniel");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("lastName", "Chavez");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("email", "daniel@test.com");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("cellphone", "0997188088");
    }

}
