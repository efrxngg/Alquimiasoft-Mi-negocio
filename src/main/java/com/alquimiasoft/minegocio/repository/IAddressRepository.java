package com.alquimiasoft.minegocio.repository;

import com.alquimiasoft.minegocio.entity.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface IAddressRepository extends JpaRepository<AddressEntity, UUID> {
}
