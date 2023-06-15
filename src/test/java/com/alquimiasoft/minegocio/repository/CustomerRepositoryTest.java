package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.AddressEntity;
import com.alquimiasoft.minegocio.entity.CustomerEntity;
import com.alquimiasoft.minegocio.entity.IdentificationType;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;

@Log4j2
@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CustomerRepository clientRepository;

    /**
     * Funcionalidad para buscar y obtener un listado de clientes.
     */
    @Test
    void testFindByIdentificationNumberOrName() {
        var client = CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .build();
        entityManager.persist(client);
        List<CustomerEntity> clients = Collections.emptyList();
        clients.forEach(c -> log.info(" Client Result: {}", c));

        assertThat(clients).hasSize(1);
    }

    /**
     * Funcionalidad para crear un nuevo cliente con la dirección matriz
     */
    @Test
    void testSaveClientWithAddress() {
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
        log.info("Client Result: {}", clientResult);

        assertThat(clientResult).hasFieldOrPropertyWithValue("identificationNumber", "0954943122");
    }

    /**
     * Funcionalidad para eliminar un cliente
     */
    @Test
    void testDeleteClient() {
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
        log.info("Client Result: {}", clientResult);

        clientRepository.deleteById(clientResult.getId());
        boolean exists = clientRepository.existsById(clientResult.getId());
        assertFalse(exists);
    }

    @Test
    void testSaveClient() {
        var client = CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .build();
        CustomerEntity clientResult = CustomerEntity.builder().build();
        log.info("Client Result: {}", clientResult);

        assertThat(clientResult).hasFieldOrPropertyWithValue("identificationNumber", "0954943122");
    }

    @Test
    void testFindAll() {
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
        entityManager.persist(client);
        List<CustomerEntity> clients = Collections.emptyList();

        assertThat(clients).hasSize(1);
    }

    @Test
    void testFindByIdentificationNumberOrNameWitoutName() {
        var client = CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .build();
        entityManager.persist(client);
        List<CustomerEntity> clients = Collections.emptyList();
        clients.forEach(c -> log.info(" Client Result: {}", c));

        assertThat(clients).hasSize(1);
    }

    @Test
    void testFindByIdentificationNumberOrNameWithoutIdentificationNumber() {
        var client = CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .build();
        entityManager.persist(client);
        List<CustomerEntity> clients = Collections.emptyList();
        clients.forEach(c -> log.info(" Client Result: {}", c));

        assertThat(clients).hasSize(1);
    }

}