package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TbDatafileent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TbDatafileent.
 */
public interface TbDatafileentService {

    /**
     * Save a tbDatafileent.
     *
     * @param tbDatafileent the entity to save
     * @return the persisted entity
     */
    TbDatafileent save(TbDatafileent tbDatafileent);

    /**
     * Get all the tbDatafileents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TbDatafileent> findAll(Pageable pageable);

    /**
     * Get the "id" tbDatafileent.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TbDatafileent findOne(Long id);

    /**
     * Delete the "id" tbDatafileent.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
