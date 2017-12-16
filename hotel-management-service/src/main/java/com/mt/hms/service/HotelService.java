package com.mt.hms.service;

import java.util.List;

import com.mt.hms.dto.HotelDetailsDTO;

public interface HotelService {

	List<HotelDetailsDTO> getAllHotels();

	HotelDetailsDTO getHotelById(Long hotelId);

	HotelDetailsDTO createHotel(HotelDetailsDTO dto);

	HotelDetailsDTO updateHotel(HotelDetailsDTO dto);

	List<HotelDetailsDTO> updateHotels(List<HotelDetailsDTO> dtos);

}
