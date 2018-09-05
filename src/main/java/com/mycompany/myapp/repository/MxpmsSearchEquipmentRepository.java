package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MxpmsSearchEquipment;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MxpmsSearchEquipment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MxpmsSearchEquipmentRepository extends JpaRepository<MxpmsSearchEquipment, Long> {

}
