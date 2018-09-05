package com.mycompany.myapp.service;

import com.mycompany.myapp.service.dto.TbEDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TbE.
 */
public interface TbEService {

    /**
     * Save a tbE.
     *
     * @param tbEDTO the entity to save
     * @return the persisted entity
     */
    TbEDTO save(TbEDTO tbEDTO);

    /**
     * Get all the tbES.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TbEDTO> findAll(Pageable pageable);

    /**
     * Get the "id" tbE.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TbEDTO findOne(Long id);

    /**
     * Delete the "id" tbE.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
