package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.service.MxpmsSearchEquipmentService;
import com.mycompany.myapp.domain.MxpmsSearchEquipment;
import com.mycompany.myapp.repository.MxpmsSearchEquipmentRepository;
import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;
import com.mycompany.myapp.service.mapper.MxpmsSearchEquipmentMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Service Implementation for managing MxpmsSearchEquipment.
 */
@Service
@Transactional
public class MxpmsSearchEquipmentServiceImpl implements MxpmsSearchEquipmentService {

    private final Logger log = LoggerFactory.getLogger(MxpmsSearchEquipmentServiceImpl.class);

    private final MxpmsSearchEquipmentRepository mxpmsSearchEquipmentRepository;

    private final MxpmsSearchEquipmentMapper mxpmsSearchEquipmentMapper;

    public MxpmsSearchEquipmentServiceImpl(MxpmsSearchEquipmentRepository mxpmsSearchEquipmentRepository, MxpmsSearchEquipmentMapper mxpmsSearchEquipmentMapper) {
        this.mxpmsSearchEquipmentRepository = mxpmsSearchEquipmentRepository;
        this.mxpmsSearchEquipmentMapper = mxpmsSearchEquipmentMapper;
    }

    /**
     * Save a mxpmsSearchEquipment.
     *
     * @param mxpmsSearchEquipmentDTO the entity to save
     * @return the persisted entity
     */
    @Override
    public MxpmsSearchEquipmentDTO save(MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO) {
        log.debug("Request to save MxpmsSearchEquipment : {}", mxpmsSearchEquipmentDTO);
        MxpmsSearchEquipment mxpmsSearchEquipment = mxpmsSearchEquipmentMapper.toEntity(mxpmsSearchEquipmentDTO);
        mxpmsSearchEquipment = mxpmsSearchEquipmentRepository.save(mxpmsSearchEquipment);
        return mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
    }

    /**
     * Get all the mxpmsSearchEquipments.
     *
     * @param pageable the pagination information
     * @return the list of entities
     */
    @Override
    @Transactional(readOnly = true)
    public Page<MxpmsSearchEquipmentDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MxpmsSearchEquipments");
        return mxpmsSearchEquipmentRepository.findAll(pageable)
            .map(mxpmsSearchEquipmentMapper::toDto);
    }

    /**
     * Get one mxpmsSearchEquipment by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    @Override
    @Transactional(readOnly = true)
    public MxpmsSearchEquipmentDTO findOne(Long id) {
        log.debug("Request to get MxpmsSearchEquipment : {}", id);
        MxpmsSearchEquipment mxpmsSearchEquipment = mxpmsSearchEquipmentRepository.findOne(id);
        return mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
    }

    /**
     * Delete the mxpmsSearchEquipment by id.
     *
     * @param id the id of the entity
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete MxpmsSearchEquipment : {}", id);
        mxpmsSearchEquipmentRepository.delete(id);
    }
}
