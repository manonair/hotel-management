package com.mt.hms.resource;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.hateoas.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.mt.hms.dto.HotelDetailsDTO;
import com.mt.hms.service.HotelService;

//@RefreshScope
@RestController
@RequestMapping(value = "/hotel", produces = { "application/hal+json", "application/json" })
public class HotelResource {

	private static final Logger LOGGER = LoggerFactory.getLogger(HotelResource.class);

	@Autowired
	private HotelService hotelService;

	@RequestMapping(value = "/{hotelId}", method = RequestMethod.GET)
	public ResponseEntity<Resource<HotelDetailsDTO>> getSubscription(@PathVariable("hotelId") Long hotelId) {
		HotelDetailsDTO dto = hotelService.getHotelById(hotelId);
		if (dto == null) {
			LOGGER.warn("No Hotel found for hotelId: {}", hotelId);
			return new ResponseEntity<Resource<HotelDetailsDTO>>(HttpStatus.NOT_FOUND);
		}
		Resource<HotelDetailsDTO> resource = new Resource<HotelDetailsDTO>(dto);
		return new ResponseEntity<Resource<HotelDetailsDTO>>(resource, HttpStatus.OK);
	}

	@RequestMapping(value = "/hotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Resource<HotelDetailsDTO>>> updateUserGroupsMembership(
			@RequestBody List<HotelDetailsDTO> detailsDTOs) {
		List<HotelDetailsDTO> userGroupDTOs = hotelService.updateHotels(detailsDTOs);
		return transformHotelDTOs(userGroupDTOs, HttpStatus.OK);
	}

	@RequestMapping(value = "/allhotels", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<List<Resource<HotelDetailsDTO>>> getAllHotels() {
		LOGGER.info("*** getAllHotels ***");
		List<HotelDetailsDTO> userGroupDTOs = hotelService.getAllHotels();
		return transformHotelDTOs(userGroupDTOs, HttpStatus.OK);
	}

	private ResponseEntity<List<Resource<HotelDetailsDTO>>> transformHotelDTOs(List<HotelDetailsDTO> hotelDetailsDTOs,
			HttpStatus status) {
		List<Resource<HotelDetailsDTO>> resources = transformList(hotelDetailsDTOs);
		return new ResponseEntity<List<Resource<HotelDetailsDTO>>>(resources, status);
	}

	public List<Resource<HotelDetailsDTO>> transformList(List<HotelDetailsDTO> resources) {
		if (resources == null) {
			return null;
		}
		return resources.stream().map(resource -> this.transform(resource)).collect(Collectors.toList());
	}

	public Resource<HotelDetailsDTO> transform(HotelDetailsDTO resource) {
		if (resource == null) {
			return null;
		}

		return new Resource<>(resource);
	}

}
