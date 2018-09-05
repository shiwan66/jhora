package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MaskService;
import com.mycompany.myapp.domain.Mask;
import com.mycompany.myapp.repository.MaskRepository;
import com.mycompany.myapp.service.dto.MaskDTO;
import com.mycompany.myapp.service.mapper.MaskMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing Mask.
 */
@Service
@Transactional
public class MaskServiceImpl implements MaskService {

    private final Logger log = LoggerFactory.getLogger(MaskServiceImpl.class);

    private final MaskRepository maskRepository;

    private final MaskMapper maskMapper;

    public MaskServiceImpl(MaskRepository maskRepository, MaskMapper maskMapper) {
        this.maskRepository = maskRepository;
        this.maskMapper = maskMapper;
    }

    /**
     * Save a mask.
     *
     * @param maskDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MaskDTO save(MaskDTO maskDTO) {
        log.debug("Request to save Mask : {}", maskDTO);
        Mask mask = maskMapper.toEntity(maskDTO);
        mask = maskRepository.save(mask);
        return maskMapper.toDto(mask);
    }

    /**
     * Get all the masks.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MaskDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Masks");
        return maskRepository.findAll(pageable)
            .map(maskMapper::toDto);
    }

    /**
     * Get one mask by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MaskDTO findOne(Long id) {
        log.debug("Request to get Mask : {}", id);
        Mask mask = maskRepository.findOne(id);
        return maskMapper.toDto(mask);
    }

    /**
     * Delete the mask by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Mask : {}", id);
        maskRepository.delete(id);
    }
}
