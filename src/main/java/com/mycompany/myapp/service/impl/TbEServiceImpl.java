package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.TbEService;
import com.mycompany.myapp.domain.TbE;
import com.mycompany.myapp.repository.TbERepository;
import com.mycompany.myapp.service.dto.TbEDTO;
import com.mycompany.myapp.service.mapper.TbEMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing TbE.
 */
@Service
@Transactional
public class TbEServiceImpl implements TbEService {

    private final Logger log = LoggerFactory.getLogger(TbEServiceImpl.class);

    private final TbERepository tbERepository;

    private final TbEMapper tbEMapper;

    public TbEServiceImpl(TbERepository tbERepository, TbEMapper tbEMapper) {
        this.tbERepository = tbERepository;
        this.tbEMapper = tbEMapper;
    }

    /**
     * Save a tbE.
     *
     * @param tbEDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public TbEDTO save(TbEDTO tbEDTO) {
        log.debug("Request to save TbE : {}", tbEDTO);
        TbE tbE = tbEMapper.toEntity(tbEDTO);
        tbE = tbERepository.save(tbE);
        return tbEMapper.toDto(tbE);
    }

    /**
     * Get all the tbES.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TbEDTO> findAll(Pageable pageable) {
        log.debug("Request to get all TbES");
        return tbERepository.findAll(pageable)
            .map(tbEMapper::toDto);
    }

    /**
     * Get one tbE by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public TbEDTO findOne(Long id) {
        log.debug("Request to get TbE : {}", id);
        TbE tbE = tbERepository.findOne(id);
        return tbEMapper.toDto(tbE);
    }

    /**
     * Delete the tbE by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TbE : {}", id);
        tbERepository.delete(id);
    }
}
