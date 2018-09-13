package com.mycompany.myapp.repository;

import com.mycompany.myapp.domain.MxpmsSearchEquipment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.*;


/**
 * Spring Data JPA repository for the MxpmsSearchEquipment entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MxpmsSearchEquipmentRepository extends JpaRepository<MxpmsSearchEquipment, Long> {

    @Query(value = "SELECT * FROM MXPMS_SEARCH_EQUIPMENT WHERE PID = ?1 ORDER BY ?#{#pageable}",
        countQuery = "SELECT count(*) FROM MXPMS_SEARCH_EQUIPMENT WHERE PID = ?1 ORDER BY ?#{#pageable}",
        nativeQuery = true)
    Page<MxpmsSearchEquipment> findByPid(String pid, Pageable pageable);


}
