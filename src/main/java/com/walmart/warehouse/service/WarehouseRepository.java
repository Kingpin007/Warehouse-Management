package com.walmart.warehouse.service;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.walmart.warehouse.domain.WarehouseDO;


@Repository
public interface WarehouseRepository
        extends JpaRepository<WarehouseDO, UUID>{
	
}
