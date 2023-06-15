package com.alquimiasoft.minegocio.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

@Log4j2
@ActiveProfiles("dev")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class AddressRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private IClientRepository clientRepository;

    /**
     * Funcionalidad para registrar una nueva dirección por cliente
     */
    @Test
    void testSaveNewAddressForClient() {

    }

    /**
     * Funcionalidad para listar las direcciones adicionales del cliente
     */
    @Test
    void testFindAllAddressByClient() {

    }

}
