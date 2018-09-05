package com.mycompany.myapp.service;

import com.mycompany.myapp.domain.DBFile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing DBFile.
 */
public interface DBFileService {

    /**
     * Save a dBFile.
     *
     * @param dBFile the entity to save
     * @return the persisted entity
     */
    DBFile save(DBFile dBFile);

    /**
     * Get all the dBFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    Page<DBFile> findAll(Pageable pageable);

    /**
     * Get the "id" dBFile.
     *
     * @param id the id of the entity
     * @return the entity
     */
    DBFile findOne(Long id);

    /**
     * Delete the "id" dBFile.
     *
     * @param id the id of the entity
     */
    void delete(Long id);
}
