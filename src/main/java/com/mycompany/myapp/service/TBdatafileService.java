package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.TBdatafile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing TBdatafile.
 */
public interface TBdatafileService {

    /**
     * Save a tBdatafile.
     *
     * @param tBdatafile the entity to save
     * @return the persisted entity
     */
    TBdatafile save(TBdatafile tBdatafile);

    /**
     * Get all the tBdatafiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<TBdatafile> findAll(Pageable pageable);

    /**
     * Get the "id" tBdatafile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    TBdatafile findOne(Long id);

    /**
     * Delete the "id" tBdatafile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
