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
    private CustomerRepository customerRepository;

    /**
     * Funcionalidad para buscar y obtener un listado de customeres.
     */
    @Test
    void testFindByIdentificationNumberOrName() {
        CustomerEntity customer = getCustomerEntity();
        entityManager.persist(customer);
        List<CustomerEntity> customers = customerRepository.findAllByIdentificationNumberOrName("0954943122", "Efren Galarza");
        customers.forEach(c -> log.info(" Customer Result: {}", c));

        assertThat(customers).hasSize(1);
    }

    /**
     * Funcionalidad para crear un nuevo customere con la dirección matriz
     */
    @Test
    void testSaveCustomerWithAddress() {
        CustomerEntity customer = getCustomerEntity();
        CustomerEntity customerResult = entityManager.persist(customer);
        log.info("Customer Result: {}", customerResult);

        assertThat(customerResult).hasFieldOrPropertyWithValue("identificationNumber", "0954943122");
    }

    /**
     * Funcionalidad para eliminar un customere
     */
    @Test
    void testDeleteCustomer() {
        var customer = getCustomerEntity();
        CustomerEntity customerResult = entityManager.persist(customer);
        log.info("Customer Result: {}", customerResult);

        customerRepository.deleteById(customerResult.getId());
        boolean exists = customerRepository.existsById(customerResult.getId());
        assertFalse(exists);
    }

    /**
     * Funcionalidad para listar las direcciones adicionales del cliente
     */
    @Test
    void testFindAllAddressByClient() {
        CustomerEntity customer = getCustomerEntity();
        CustomerEntity customerResult = entityManager.persist(customer);
        log.info("Customer Result: {}", customerResult);

        List<AddressEntity> addresses = customerRepository.findById(customerResult.getId())
                .map(CustomerEntity::getAddresses).orElse(Collections.emptyList());
        assertThat(addresses).hasSize(1);
    }

    @Test
    void testSaveCustomer() {
        CustomerEntity customer = getCustomerEntity();
        CustomerEntity customerResult = entityManager.persist(customer);
        log.info("Customer Result: {}", customerResult);

        assertThat(customerResult).hasFieldOrPropertyWithValue("identificationNumber", "0954943122");
    }

    @Test
    void testFindAll() {
        CustomerEntity customer = getCustomerEntity();
        entityManager.persist(customer);
        List<CustomerEntity> customers = customerRepository.findAll();

        assertThat(customers).hasSize(1);
    }

    @Test
    void testFindByIdentificationNumberOrNameWitoutName() {
        CustomerEntity customer = getCustomerEntity();
        entityManager.persist(customer);
        List<CustomerEntity> customers = customerRepository
                .findAllByIdentificationNumberOrName("0954943122", null);
        customers.forEach(c -> log.info(" Customer Result: {}", c));

        assertThat(customers).hasSize(1);
    }

    @Test
    void testFindByIdentificationNumberOrNameWithoutIdentificationNumber() {
        CustomerEntity customer = getCustomerEntity();
        entityManager.persist(customer);
        List<CustomerEntity> customers = customerRepository
                .findAllByIdentificationNumberOrName(null, "Efren Galarza");
        customers.forEach(c -> log.info(" Customer Result: {}", c));

        assertThat(customers).hasSize(1);
    }

    private static CustomerEntity getCustomerEntity() {
        var address = AddressEntity.builder()
                .province("Guayas").city("Guayaquil").address("M-x Sl-x")
                .build();
        return CustomerEntity.builder()
                .firstName("Efren").lastName("Galarza")
                .email("efren@test.com")
                .cellphone("099718800")
                .identificationType(IdentificationType.CED)
                .identificationNumber("0954943122")
                .addresses(List.of(address))
                .mainAddress(address)
                .build();
    }
}