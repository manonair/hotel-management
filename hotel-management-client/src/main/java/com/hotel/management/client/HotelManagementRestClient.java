package com.hotel.management.client;

import org.hotelmanagement.core.dto.HotelDetailsDTO;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

/**
 * @author Manoj
 */
public interface HotelManagementRestClient {
   

    @RequestLine("GET /testprep//{hotelId}")
    @Headers({"Authorization: {authorization}" })
	public HotelDetailsDTO getSubscription(@Param("hotelId") Long hotelId);




}
