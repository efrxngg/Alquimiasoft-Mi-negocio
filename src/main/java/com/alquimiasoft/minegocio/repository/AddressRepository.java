package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddressRepository extends JpaRepository<AddressEntity, UUID> {
}
