package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.DBFileService;
import com.mycompany.myapp.domain.DBFile;
import com.mycompany.myapp.repository.DBFileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing DBFile.
 */
@Service
@Transactional
public class DBFileServiceImpl implements DBFileService {

    private final Logger log = LoggerFactory.getLogger(DBFileServiceImpl.class);

    private final DBFileRepository dBFileRepository;

    public DBFileServiceImpl(DBFileRepository dBFileRepository) {
        this.dBFileRepository = dBFileRepository;
    }

    /**
     * Save a dBFile.
     *
     * @param dBFile the entity to save
     * @return the persisted entity
     */
    @Override
    public DBFile save(DBFile dBFile) {
        log.debug("Request to save DBFile : {}", dBFile);
        return dBFileRepository.save(dBFile);
    }

    /**
     * Get all the dBFiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DBFile> findAll(Pageable pageable) {
        log.debug("Request to get all DBFiles");
        return dBFileRepository.findAll(pageable);
    }

    /**
     * Get one dBFile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public DBFile findOne(Long id) {
        log.debug("Request to get DBFile : {}", id);
        return dBFileRepository.findOne(id);
    }

    /**
     * Delete the dBFile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DBFile : {}", id);
        dBFileRepository.delete(id);
    }
}
