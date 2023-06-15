package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface IClientRepository extends JpaRepository<ClientEntity, UUID> {
    @Query(value = "SELECT * FROM client_entity WHERE identification_number = :identificationNumber OR CONCAT(first_name,' ',last_name) = :name", nativeQuery = true)
    List<ClientEntity> findAllByIdentificationNumberOrName(@Param("identificationNumber") String identificationNumber, @Param("name") String name);

}
