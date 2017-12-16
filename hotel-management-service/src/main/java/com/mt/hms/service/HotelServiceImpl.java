package com.mt.hms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mt.hms.domin.entity.HotelDetails;
import com.mt.hms.dto.HotelDetailsDTO;
import com.mt.hms.repository.SpringDataHotelDetailsRepository;

@Service
@Transactional
public class HotelServiceImpl implements HotelService {

	@Autowired
	private SpringDataHotelDetailsRepository repository;

	@Override
	public List<HotelDetailsDTO> getAllHotels() {
		List<HotelDetailsDTO> hotelDetailsDTOs = convertHotelDetailsForResult(repository.findAll());
		return hotelDetailsDTOs;
	}

	@Override
	public HotelDetailsDTO getHotelById(Long hotelId) {
		return mapToDTO(repository.findHotelDetailsById(hotelId));
	}

	@Override
	public HotelDetailsDTO createHotel(HotelDetailsDTO dto) {
		HotelDetails hotelDetails = repository.save(convertHotel(new HotelDetails(), dto));
		return mapToDTO(hotelDetails);
	}

	@Override
	public HotelDetailsDTO updateHotel(HotelDetailsDTO dto) {
		HotelDetails hotelDetails = processHotelUpdate(dto);
		return mapToDTO(hotelDetails);
	}

	private HotelDetails processHotelUpdate(HotelDetailsDTO dto) {
		HotelDetails hotelDetails = repository.findHotelDetailsById(dto.getId());
		if (null == hotelDetails) {
			hotelDetails = new HotelDetails();
		}
		return repository.save(convertHotel(hotelDetails, dto));
	}

	@Override
	public List<HotelDetailsDTO> updateHotels(List<HotelDetailsDTO> dtos) {
		List<HotelDetails> hotels = dtos.stream().map(dto -> processHotelUpdate(dto)).collect(Collectors.toList());

		return hotels.stream().map(hotel -> mapToDTO(hotel)).collect(Collectors.toList());

	}

	private List<HotelDetailsDTO> convertHotelDetailsForResult(Iterable<HotelDetails> hotels) {
		List<HotelDetailsDTO> convertedHotels = new ArrayList<HotelDetailsDTO>();
		if (hotels != null) {
			hotels.forEach(hotel -> convertedHotels.add(mapToDTO(hotel)));
		}
		return convertedHotels;
	}

	private HotelDetailsDTO mapToDTO(HotelDetails hotelDetails) {
		HotelDetailsDTO dto=null;
		if(null!=hotelDetails){
			dto= HotelDetailsDTO.builder().address(hotelDetails.getAddress()).description(hotelDetails.getDescription())
					.hotelName(hotelDetails.getHotelName()).id(hotelDetails.getId()).created(hotelDetails.getCreated())
					.updated(hotelDetails.getUpdated()).build();
		}
		return dto;
	}

	protected HotelDetails convertHotel(HotelDetails hotel, HotelDetailsDTO dto) {
		hotel.setAddress(dto.getAddress());
		hotel.setDescription(dto.getDescription());
		hotel.setStatus(dto.getStatus());
		hotel.setCreated(dto.getCreated());
		hotel.setUpdated(dto.getUpdated());
		return hotel;
	}

}
