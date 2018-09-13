package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing MxpmsSearchEquipment.
 */
public interface MxpmsSearchEquipmentService {

    /**
     * Save a mxpmsSearchEquipment.
     *
     * @param mxpmsSearchEquipmentDTO the entity to save
     * @return the persisted entity
     */
    MxpmsSearchEquipmentDTO save(MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO);

    /**
     * Get all the mxpmsSearchEquipments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MxpmsSearchEquipmentDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mxpmsSearchEquipment.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MxpmsSearchEquipmentDTO findOne(Long id);

    /**
     * Delete the "id" mxpmsSearchEquipment.
     *
     * @param id the id of the entity
     */
    void delete(Long id);

    Page<MxpmsSearchEquipmentDTO> findByPid(String pid,Pageable pageable);
}
