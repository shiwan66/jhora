package com.mycompany.myapp.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.mycompany.myapp.domain.MxpmsSearchEquipment;

import com.mycompany.myapp.repository.MxpmsSearchEquipmentRepository;
import com.mycompany.myapp.web.rest.errors.BadRequestAlertException;
import com.mycompany.myapp.web.rest.util.HeaderUtil;
import com.mycompany.myapp.web.rest.util.PaginationUtil;
import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;
import com.mycompany.myapp.service.mapper.MxpmsSearchEquipmentMapper;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing MxpmsSearchEquipment.
 */
@RestController
@RequestMapping("/api")
public class MxpmsSearchEquipmentResource {

    private final Logger log = LoggerFactory.getLogger(MxpmsSearchEquipmentResource.class);

    private static final String ENTITY_NAME = "mxpmsSearchEquipment";

    private final MxpmsSearchEquipmentRepository mxpmsSearchEquipmentRepository;

    private final MxpmsSearchEquipmentMapper mxpmsSearchEquipmentMapper;

    public MxpmsSearchEquipmentResource(MxpmsSearchEquipmentRepository mxpmsSearchEquipmentRepository, MxpmsSearchEquipmentMapper mxpmsSearchEquipmentMapper) {
        this.mxpmsSearchEquipmentRepository = mxpmsSearchEquipmentRepository;
        this.mxpmsSearchEquipmentMapper = mxpmsSearchEquipmentMapper;
    }

    /**
     * POST  /mxpms-search-equipments : Create a new mxpmsSearchEquipment.
     *
     * @param mxpmsSearchEquipmentDTO the mxpmsSearchEquipmentDTO to create
     * @return the ResponseEntity with status 201 (Created) and with body the new mxpmsSearchEquipmentDTO, or with status 400 (Bad Request) if the mxpmsSearchEquipment has already an ID
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PostMapping("/mxpms-search-equipments")
    @Timed
    public ResponseEntity<MxpmsSearchEquipmentDTO> createMxpmsSearchEquipment(@RequestBody MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO) throws URISyntaxException {
        log.debug("REST request to save MxpmsSearchEquipment : {}", mxpmsSearchEquipmentDTO);
        if (mxpmsSearchEquipmentDTO.getId() != null) {
            throw new BadRequestAlertException("A new mxpmsSearchEquipment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        MxpmsSearchEquipment mxpmsSearchEquipment = mxpmsSearchEquipmentMapper.toEntity(mxpmsSearchEquipmentDTO);
        mxpmsSearchEquipment = mxpmsSearchEquipmentRepository.save(mxpmsSearchEquipment);
        MxpmsSearchEquipmentDTO result = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
        return ResponseEntity.created(new URI("/api/mxpms-search-equipments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * PUT  /mxpms-search-equipments : Updates an existing mxpmsSearchEquipment.
     *
     * @param mxpmsSearchEquipmentDTO the mxpmsSearchEquipmentDTO to update
     * @return the ResponseEntity with status 200 (OK) and with body the updated mxpmsSearchEquipmentDTO,
     * or with status 400 (Bad Request) if the mxpmsSearchEquipmentDTO is not valid,
     * or with status 500 (Internal Server Error) if the mxpmsSearchEquipmentDTO couldn't be updated
     * @throws URISyntaxException if the Location URI syntax is incorrect
     */
    @PutMapping("/mxpms-search-equipments")
    @Timed
    public ResponseEntity<MxpmsSearchEquipmentDTO> updateMxpmsSearchEquipment(@RequestBody MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO) throws URISyntaxException {
        log.debug("REST request to update MxpmsSearchEquipment : {}", mxpmsSearchEquipmentDTO);
        if (mxpmsSearchEquipmentDTO.getId() == null) {
            return createMxpmsSearchEquipment(mxpmsSearchEquipmentDTO);
        }
        MxpmsSearchEquipment mxpmsSearchEquipment = mxpmsSearchEquipmentMapper.toEntity(mxpmsSearchEquipmentDTO);
        mxpmsSearchEquipment = mxpmsSearchEquipmentRepository.save(mxpmsSearchEquipment);
        MxpmsSearchEquipmentDTO result = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME, mxpmsSearchEquipmentDTO.getId().toString()))
            .body(result);
    }

    /**
     * GET  /mxpms-search-equipments : get all the mxpmsSearchEquipments.
     *
     * @param pageable the pagination information
     * @return the ResponseEntity with status 200 (OK) and the list of mxpmsSearchEquipments in body
     */
    @GetMapping("/mxpms-search-equipments")
    @Timed
    public ResponseEntity<List<MxpmsSearchEquipmentDTO>> getAllMxpmsSearchEquipments(Pageable pageable) {
        log.debug("REST request to get a page of MxpmsSearchEquipments");
        Page<MxpmsSearchEquipment> page = mxpmsSearchEquipmentRepository.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/mxpms-search-equipments");
        return new ResponseEntity<>(mxpmsSearchEquipmentMapper.toDto(page.getContent()), headers, HttpStatus.OK);
    }

    /**
     * GET  /mxpms-search-equipments/:id : get the "id" mxpmsSearchEquipment.
     *
     * @param id the id of the mxpmsSearchEquipmentDTO to retrieve
     * @return the ResponseEntity with status 200 (OK) and with body the mxpmsSearchEquipmentDTO, or with status 404 (Not Found)
     */
    @GetMapping("/mxpms-search-equipments/{id}")
    @Timed
    public ResponseEntity<MxpmsSearchEquipmentDTO> getMxpmsSearchEquipment(@PathVariable Long id) {
        log.debug("REST request to get MxpmsSearchEquipment : {}", id);
        MxpmsSearchEquipment mxpmsSearchEquipment = mxpmsSearchEquipmentRepository.findOne(id);
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
        return ResponseUtil.wrapOrNotFound(Optional.ofNullable(mxpmsSearchEquipmentDTO));
    }

    /**
     * DELETE  /mxpms-search-equipments/:id : delete the "id" mxpmsSearchEquipment.
     *
     * @param id the id of the mxpmsSearchEquipmentDTO to delete
     * @return the ResponseEntity with status 200 (OK)
     */
    @DeleteMapping("/mxpms-search-equipments/{id}")
    @Timed
    public ResponseEntity<Void> deleteMxpmsSearchEquipment(@PathVariable Long id) {
        log.debug("REST request to delete MxpmsSearchEquipment : {}", id);
        mxpmsSearchEquipmentRepository.delete(id);
        return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString())).build();
    }
}
