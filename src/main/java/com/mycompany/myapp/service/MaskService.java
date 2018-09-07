package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.MaskDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing Mask.
 */
public interface MaskService {

    /**
     * Save a mask.
     *
     * @param maskDTO the entity to save
     * @return the persisted entity
     */
    MaskDTO save(MaskDTO maskDTO);

    /**
     * Get all the masks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<MaskDTO> findAll(Pageable pageable);

    /**
     * Get the "id" mask.
     *
     * @param id the id of the entity
     * @return the entity
     */
    MaskDTO findOne(Long id);

    /**
     * Delete the "id" mask.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
