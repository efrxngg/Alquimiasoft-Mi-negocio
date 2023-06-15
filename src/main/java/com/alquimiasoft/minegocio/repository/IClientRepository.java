package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

public interface IClientRepository extends JpaRepository<ClientEntity, UUID> {
    @Query(value = "SELECT * FROM client_entity WHERE identificationNumber = :identification OR CONCAT(firstName, lastName) = :name", nativeQuery = true)
    Optional<ClientEntity> findByIdentificationNumberOrName(@Param("identification") String identification, @Param("name") String name);

}
