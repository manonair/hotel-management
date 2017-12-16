package com.mt.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.hms.domin.entity.MenuDetails;

/**
 * @author m1015830
 */
public interface SpringDataMenuDetailsRepository extends CrudRepository<MenuDetails, Long> {

	MenuDetails findMenuDetailsById(Long menuId);

}
