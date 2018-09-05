package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TBdatafileService;
import com.mycompany.myapp.domain.TBdatafile;
import com.mycompany.myapp.repository.TBdatafileRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TBdatafile.
 */
@Service
@Transactional
public class TBdatafileServiceImpl implements TBdatafileService {

    private final Logger log = LoggerFactory.getLogger(TBdatafileServiceImpl.class);

    private final TBdatafileRepository tBdatafileRepository;

    public TBdatafileServiceImpl(TBdatafileRepository tBdatafileRepository) {
        this.tBdatafileRepository = tBdatafileRepository;
    }

    /**
     * Save a tBdatafile.
     *
     * @param tBdatafile the entity to save
     * @return the persisted entity
     */
    @Override
    public TBdatafile save(TBdatafile tBdatafile) {
        log.debug("Request to save TBdatafile : {}", tBdatafile);
        return tBdatafileRepository.save(tBdatafile);
    }

    /**
     * Get all the tBdatafiles.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TBdatafile> findAll(Pageable pageable) {
        log.debug("Request to get all TBdatafiles");
        return tBdatafileRepository.findAll(pageable);
    }

    /**
     * Get one tBdatafile by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TBdatafile findOne(Long id) {
        log.debug("Request to get TBdatafile : {}", id);
        return tBdatafileRepository.findOne(id);
    }

    /**
     * Delete the tBdatafile by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TBdatafile : {}", id);
        tBdatafileRepository.delete(id);
    }
}
