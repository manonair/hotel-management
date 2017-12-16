package com.mt.hms.repository;

import org.springframework.data.repository.CrudRepository;

import com.mt.hms.domin.entity.TableType;

/**
 * @author m1015830
 */
public interface SpringDataTableTypeRepository extends CrudRepository<TableType, Long> {

	TableType findTableTypeById(Long tableTypeId);

}
