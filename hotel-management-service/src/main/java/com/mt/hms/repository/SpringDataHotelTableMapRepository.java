package com.mt.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.hms.domin.entity.HotelTableMap;

/**
 * @author m1015830
 */
public interface SpringDataHotelTableMapRepository extends CrudRepository<HotelTableMap, Long> {

	HotelTableMap findHotelTableMapById(Long mapId);

}
