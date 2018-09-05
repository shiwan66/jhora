package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TbDatafileentService;
import com.mycompany.myapp.domain.TbDatafileent;
import com.mycompany.myapp.repository.TbDatafileentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TbDatafileent.
 */
@Service
@Transactional
public class TbDatafileentServiceImpl implements TbDatafileentService {

    private final Logger log = LoggerFactory.getLogger(TbDatafileentServiceImpl.class);

    private final TbDatafileentRepository tbDatafileentRepository;

    public TbDatafileentServiceImpl(TbDatafileentRepository tbDatafileentRepository) {
        this.tbDatafileentRepository = tbDatafileentRepository;
    }

    /**
     * Save a tbDatafileent.
     *
     * @param tbDatafileent the entity to save
     * @return the persisted entity
     */
    @Override
    public TbDatafileent save(TbDatafileent tbDatafileent) {
        log.debug("Request to save TbDatafileent : {}", tbDatafileent);
        return tbDatafileentRepository.save(tbDatafileent);
    }

    /**
     * Get all the tbDatafileents.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TbDatafileent> findAll(Pageable pageable) {
        log.debug("Request to get all TbDatafileents");
        return tbDatafileentRepository.findAll(pageable);
    }

    /**
     * Get one tbDatafileent by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TbDatafileent findOne(Long id) {
        log.debug("Request to get TbDatafileent : {}", id);
        return tbDatafileentRepository.findOne(id);
    }

    /**
     * Delete the tbDatafileent by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TbDatafileent : {}", id);
        tbDatafileentRepository.delete(id);
    }
}
