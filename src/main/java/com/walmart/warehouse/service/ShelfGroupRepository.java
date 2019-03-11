package com.walmart.warehouse.service;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.walmart.warehouse.domain.ShelfGroupDO;

@Repository
public interface ShelfGroupRepository
        extends JpaRepository<ShelfGroupDO, UUID>{
}
