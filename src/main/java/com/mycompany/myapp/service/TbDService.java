package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TbD;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TbD.
 */
public interface TbDService {

    /**
     * Save a tbD.
     *
     * @param tbD the entity to save
     * @return the persisted entity
     */
    TbD save(TbD tbD);

    /**
     * Get all the tbDS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TbD> findAll(Pageable pageable);

    /**
     * Get the "id" tbD.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TbD findOne(Long id);

    /**
     * Delete the "id" tbD.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
