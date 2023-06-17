package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface CustomerRepository extends JpaRepository<CustomerEntity, UUID> {
    @Query(value = "SELECT * FROM customer_entity WHERE identification_number = :identificationNumber OR CONCAT(first_name,' ',last_name) LIKE %:name%", nativeQuery = true)
    List<CustomerEntity> findAllByIdentificationNumberOrName(
            @Param("identificationNumber") String identificationNumber,
            @Param("name") String name);

}
