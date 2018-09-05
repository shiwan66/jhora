package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TbDService;
import com.mycompany.myapp.domain.TbD;
import com.mycompany.myapp.repository.TbDRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TbD.
 */
@Service
@Transactional
public class TbDServiceImpl implements TbDService {

    private final Logger log = LoggerFactory.getLogger(TbDServiceImpl.class);

    private final TbDRepository tbDRepository;

    public TbDServiceImpl(TbDRepository tbDRepository) {
        this.tbDRepository = tbDRepository;
    }

    /**
     * Save a tbD.
     *
     * @param tbD the entity to save
     * @return the persisted entity
     */
    @Override
    public TbD save(TbD tbD) {
        log.debug("Request to save TbD : {}", tbD);
        return tbDRepository.save(tbD);
    }

    /**
     * Get all the tbDS.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TbD> findAll(Pageable pageable) {
        log.debug("Request to get all TbDS");
        return tbDRepository.findAll(pageable);
    }

    /**
     * Get one tbD by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TbD findOne(Long id) {
        log.debug("Request to get TbD : {}", id);
        return tbDRepository.findOne(id);
    }

    /**
     * Delete the tbD by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TbD : {}", id);
        tbDRepository.delete(id);
    }
}
