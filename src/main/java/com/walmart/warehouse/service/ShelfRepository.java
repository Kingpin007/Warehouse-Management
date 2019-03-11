package com.walmart.warehouse.service;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.walmart.warehouse.domain.ShelfDO;

@Repository
public interface ShelfRepository
        extends JpaRepository<ShelfDO, UUID>{
	
	@Query(value = "SELECT * FROM SHELF s WHERE SHELF_LENGTH_REMAINING >= ?0 AND SHELF_WIDTH_REMAINING >= ?1 AND SHELF_HEIGHT_REMAINING >= ?2", nativeQuery = true)
	public Slice<ShelfDO> findAllShelvesWithEnoughSpace(Double length, Double width, Double height, PageRequest pageRequest);
	
	@Query(value = "SELECT * FROM SHELF s WHERE SHELF_LENGTH_REMAINING >= ?0 AND SHELF_WIDTH_REMAINING >= ?1 AND SHELF_HEIGHT_REMAINING >= ?2", nativeQuery = true)
	public Slice<ShelfDO> findAllShelvesWithEnoughSpace(Double length, Double width, Double height, Pageable pageable);

	@Query(value = "SELECT * FROM SHELF s WHERE s.SHELF_LENGTH_REMAINING >= ?0 AND s.SHELF_WIDTH_REMAINING >= ?1 AND s.SHELF_HEIGHT_REMAINING >= ?2", nativeQuery = true)
	public List<ShelfDO> findAllShelvesWithEnoughSpace(Double length, Double width, Double height);

}
