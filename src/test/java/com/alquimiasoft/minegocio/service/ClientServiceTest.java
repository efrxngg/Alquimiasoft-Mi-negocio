package com.alquimiasoft.minegocio.service;

import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.ClientEntity;
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
public class ClientServiceTest {
    /**
     * Funcionalidad para editar los datos del cliente
     */
    @Test
    void testUpdateOnlyCustomerData() {
        var address = AddressEntity.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        var client = ClientEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .mainAddress(address)
                .build();
        ClientEntity clientResult = ClientEntity.builder().build();

        var clientUpdate = ClientEntity.builder()
                .id(clientResult.getId())
                .identificationNumber("57495749574594")
                .identificationType(IdentificationType.RUC)
                .firstName("Daniel")
                .lastName("Chavez")
                .email("daniel@test.com")
                .cellphone("0997188088")
                .build();
        ClientEntity clientUpateResult = ClientEntity.builder().build();

        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("firstName", "Daniel");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("lastName", "Chavez");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("email", "daniel@test.com");
        assertThat(clientUpateResult).hasFieldOrPropertyWithValue("cellphone", "0997188088");
    }

}
