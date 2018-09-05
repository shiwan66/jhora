package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.TbE;
import com.mycompany.myapp.repository.TbERepository;
import com.mycompany.myapp.service.TbEService;
import com.mycompany.myapp.service.dto.TbEDTO;
import com.mycompany.myapp.service.mapper.TbEMapper;
import com.mycompany.myapp.web.rest.errors.ExceptionTranslator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

import static com.mycompany.myapp.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Test class for the TbEResource REST controller.
 *
 * @see TbEResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class TbEResourceIntTest {

    private static final String DEFAULT_COL_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_COL_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_COL_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_COL_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private TbERepository tbERepository;

    @Autowired
    private TbEMapper tbEMapper;

    @Autowired
    private TbEService tbEService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTbEMockMvc;

    private TbE tbE;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TbEResource tbEResource = new TbEResource(tbEService);
        this.restTbEMockMvc = MockMvcBuilders.standaloneSetup(tbEResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TbE createEntity(EntityManager em) {
        TbE tbE = new TbE()
            .colFileName(DEFAULT_COL_FILE_NAME)
            .colFileType(DEFAULT_COL_FILE_TYPE);
        return tbE;
    }

    @Before
    public void initTest() {
        tbE = createEntity(em);
    }

    @Test
    @Transactional
    public void createTbE() throws Exception {
        int databaseSizeBeforeCreate = tbERepository.findAll().size();

        // Create the TbE
        TbEDTO tbEDTO = tbEMapper.toDto(tbE);
        restTbEMockMvc.perform(post("/api/tb-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbEDTO)))
            .andExpect(status().isCreated());

        // Validate the TbE in the database
        List<TbE> tbEList = tbERepository.findAll();
        assertThat(tbEList).hasSize(databaseSizeBeforeCreate + 1);
        TbE testTbE = tbEList.get(tbEList.size() - 1);
        assertThat(testTbE.getColFileName()).isEqualTo(DEFAULT_COL_FILE_NAME);
        assertThat(testTbE.getColFileType()).isEqualTo(DEFAULT_COL_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createTbEWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tbERepository.findAll().size();

        // Create the TbE with an existing ID
        tbE.setId(1L);
        TbEDTO tbEDTO = tbEMapper.toDto(tbE);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTbEMockMvc.perform(post("/api/tb-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbEDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TbE in the database
        List<TbE> tbEList = tbERepository.findAll();
        assertThat(tbEList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTbES() throws Exception {
        // Initialize the database
        tbERepository.saveAndFlush(tbE);

        // Get all the tbEList
        restTbEMockMvc.perform(get("/api/tb-es?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tbE.getId().intValue())))
            .andExpect(jsonPath("$.[*].colFileName").value(hasItem(DEFAULT_COL_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].colFileType").value(hasItem(DEFAULT_COL_FILE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTbE() throws Exception {
        // Initialize the database
        tbERepository.saveAndFlush(tbE);

        // Get the tbE
        restTbEMockMvc.perform(get("/api/tb-es/{id}", tbE.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tbE.getId().intValue()))
            .andExpect(jsonPath("$.colFileName").value(DEFAULT_COL_FILE_NAME.toString()))
            .andExpect(jsonPath("$.colFileType").value(DEFAULT_COL_FILE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTbE() throws Exception {
        // Get the tbE
        restTbEMockMvc.perform(get("/api/tb-es/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTbE() throws Exception {
        // Initialize the database
        tbERepository.saveAndFlush(tbE);
        int databaseSizeBeforeUpdate = tbERepository.findAll().size();

        // Update the tbE
        TbE updatedTbE = tbERepository.findOne(tbE.getId());
        // Disconnect from session so that the updates on updatedTbE are not directly saved in db
        em.detach(updatedTbE);
        updatedTbE
            .colFileName(UPDATED_COL_FILE_NAME)
            .colFileType(UPDATED_COL_FILE_TYPE);
        TbEDTO tbEDTO = tbEMapper.toDto(updatedTbE);

        restTbEMockMvc.perform(put("/api/tb-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbEDTO)))
            .andExpect(status().isOk());

        // Validate the TbE in the database
        List<TbE> tbEList = tbERepository.findAll();
        assertThat(tbEList).hasSize(databaseSizeBeforeUpdate);
        TbE testTbE = tbEList.get(tbEList.size() - 1);
        assertThat(testTbE.getColFileName()).isEqualTo(UPDATED_COL_FILE_NAME);
        assertThat(testTbE.getColFileType()).isEqualTo(UPDATED_COL_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTbE() throws Exception {
        int databaseSizeBeforeUpdate = tbERepository.findAll().size();

        // Create the TbE
        TbEDTO tbEDTO = tbEMapper.toDto(tbE);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTbEMockMvc.perform(put("/api/tb-es")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbEDTO)))
            .andExpect(status().isCreated());

        // Validate the TbE in the database
        List<TbE> tbEList = tbERepository.findAll();
        assertThat(tbEList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTbE() throws Exception {
        // Initialize the database
        tbERepository.saveAndFlush(tbE);
        int databaseSizeBeforeDelete = tbERepository.findAll().size();

        // Get the tbE
        restTbEMockMvc.perform(delete("/api/tb-es/{id}", tbE.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TbE> tbEList = tbERepository.findAll();
        assertThat(tbEList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TbE.class);
        TbE tbE1 = new TbE();
        tbE1.setId(1L);
        TbE tbE2 = new TbE();
        tbE2.setId(tbE1.getId());
        assertThat(tbE1).isEqualTo(tbE2);
        tbE2.setId(2L);
        assertThat(tbE1).isNotEqualTo(tbE2);
        tbE1.setId(null);
        assertThat(tbE1).isNotEqualTo(tbE2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(TbEDTO.class);
        TbEDTO tbEDTO1 = new TbEDTO();
        tbEDTO1.setId(1L);
        TbEDTO tbEDTO2 = new TbEDTO();
        assertThat(tbEDTO1).isNotEqualTo(tbEDTO2);
        tbEDTO2.setId(tbEDTO1.getId());
        assertThat(tbEDTO1).isEqualTo(tbEDTO2);
        tbEDTO2.setId(2L);
        assertThat(tbEDTO1).isNotEqualTo(tbEDTO2);
        tbEDTO1.setId(null);
        assertThat(tbEDTO1).isNotEqualTo(tbEDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(tbEMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(tbEMapper.fromId(null)).isNull();
    }
}
