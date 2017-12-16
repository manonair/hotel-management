package com.mt.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.hms.domin.entity.HotelDetails;

/**
 * @author m1015830
 */
public interface SpringDataHotelDetailsRepository extends CrudRepository<HotelDetails, Long> {

	HotelDetails findHotelDetailsById(Long hotelId);

}
