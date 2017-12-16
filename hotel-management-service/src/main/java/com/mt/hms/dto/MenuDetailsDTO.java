package com.mt.hms.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author m1015830
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
@ToString
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuDetailsDTO implements Serializable {

	private static final long serialVersionUID = 5556541763702982435L;

	 private Long id;

	private String itemName;

	private String description;

	private double price;

	private HotelDetailsDTO hotelDetails;

	private int status;

	private Date created;

	private Date updated;

}
