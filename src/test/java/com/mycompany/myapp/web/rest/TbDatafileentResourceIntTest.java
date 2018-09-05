package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.TbDatafileent;
import com.mycompany.myapp.repository.TbDatafileentRepository;
import com.mycompany.myapp.service.TbDatafileentService;
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
 * Test class for the TbDatafileentResource REST controller.
 *
 * @see TbDatafileentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class TbDatafileentResourceIntTest {

    private static final String DEFAULT_FILE_NAME = "AAAAAAAAAA";
    private static final String UPDATED_FILE_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_FILE_TYPE = "AAAAAAAAAA";
    private static final String UPDATED_FILE_TYPE = "BBBBBBBBBB";

    @Autowired
    private TbDatafileentRepository tbDatafileentRepository;

    @Autowired
    private TbDatafileentService tbDatafileentService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restTbDatafileentMockMvc;

    private TbDatafileent tbDatafileent;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final TbDatafileentResource tbDatafileentResource = new TbDatafileentResource(tbDatafileentService);
        this.restTbDatafileentMockMvc = MockMvcBuilders.standaloneSetup(tbDatafileentResource)
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
    public static TbDatafileent createEntity(EntityManager em) {
        TbDatafileent tbDatafileent = new TbDatafileent()
            .fileName(DEFAULT_FILE_NAME)
            .fileType(DEFAULT_FILE_TYPE);
        return tbDatafileent;
    }

    @Before
    public void initTest() {
        tbDatafileent = createEntity(em);
    }

    @Test
    @Transactional
    public void createTbDatafileent() throws Exception {
        int databaseSizeBeforeCreate = tbDatafileentRepository.findAll().size();

        // Create the TbDatafileent
        restTbDatafileentMockMvc.perform(post("/api/tb-datafileents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbDatafileent)))
            .andExpect(status().isCreated());

        // Validate the TbDatafileent in the database
        List<TbDatafileent> tbDatafileentList = tbDatafileentRepository.findAll();
        assertThat(tbDatafileentList).hasSize(databaseSizeBeforeCreate + 1);
        TbDatafileent testTbDatafileent = tbDatafileentList.get(tbDatafileentList.size() - 1);
        assertThat(testTbDatafileent.getFileName()).isEqualTo(DEFAULT_FILE_NAME);
        assertThat(testTbDatafileent.getFileType()).isEqualTo(DEFAULT_FILE_TYPE);
    }

    @Test
    @Transactional
    public void createTbDatafileentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = tbDatafileentRepository.findAll().size();

        // Create the TbDatafileent with an existing ID
        tbDatafileent.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTbDatafileentMockMvc.perform(post("/api/tb-datafileents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbDatafileent)))
            .andExpect(status().isBadRequest());

        // Validate the TbDatafileent in the database
        List<TbDatafileent> tbDatafileentList = tbDatafileentRepository.findAll();
        assertThat(tbDatafileentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllTbDatafileents() throws Exception {
        // Initialize the database
        tbDatafileentRepository.saveAndFlush(tbDatafileent);

        // Get all the tbDatafileentList
        restTbDatafileentMockMvc.perform(get("/api/tb-datafileents?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(tbDatafileent.getId().intValue())))
            .andExpect(jsonPath("$.[*].fileName").value(hasItem(DEFAULT_FILE_NAME.toString())))
            .andExpect(jsonPath("$.[*].fileType").value(hasItem(DEFAULT_FILE_TYPE.toString())));
    }

    @Test
    @Transactional
    public void getTbDatafileent() throws Exception {
        // Initialize the database
        tbDatafileentRepository.saveAndFlush(tbDatafileent);

        // Get the tbDatafileent
        restTbDatafileentMockMvc.perform(get("/api/tb-datafileents/{id}", tbDatafileent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(tbDatafileent.getId().intValue()))
            .andExpect(jsonPath("$.fileName").value(DEFAULT_FILE_NAME.toString()))
            .andExpect(jsonPath("$.fileType").value(DEFAULT_FILE_TYPE.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingTbDatafileent() throws Exception {
        // Get the tbDatafileent
        restTbDatafileentMockMvc.perform(get("/api/tb-datafileents/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTbDatafileent() throws Exception {
        // Initialize the database
        tbDatafileentService.save(tbDatafileent);

        int databaseSizeBeforeUpdate = tbDatafileentRepository.findAll().size();

        // Update the tbDatafileent
        TbDatafileent updatedTbDatafileent = tbDatafileentRepository.findOne(tbDatafileent.getId());
        // Disconnect from session so that the updates on updatedTbDatafileent are not directly saved in db
        em.detach(updatedTbDatafileent);
        updatedTbDatafileent
            .fileName(UPDATED_FILE_NAME)
            .fileType(UPDATED_FILE_TYPE);

        restTbDatafileentMockMvc.perform(put("/api/tb-datafileents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(updatedTbDatafileent)))
            .andExpect(status().isOk());

        // Validate the TbDatafileent in the database
        List<TbDatafileent> tbDatafileentList = tbDatafileentRepository.findAll();
        assertThat(tbDatafileentList).hasSize(databaseSizeBeforeUpdate);
        TbDatafileent testTbDatafileent = tbDatafileentList.get(tbDatafileentList.size() - 1);
        assertThat(testTbDatafileent.getFileName()).isEqualTo(UPDATED_FILE_NAME);
        assertThat(testTbDatafileent.getFileType()).isEqualTo(UPDATED_FILE_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingTbDatafileent() throws Exception {
        int databaseSizeBeforeUpdate = tbDatafileentRepository.findAll().size();

        // Create the TbDatafileent

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restTbDatafileentMockMvc.perform(put("/api/tb-datafileents")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(tbDatafileent)))
            .andExpect(status().isCreated());

        // Validate the TbDatafileent in the database
        List<TbDatafileent> tbDatafileentList = tbDatafileentRepository.findAll();
        assertThat(tbDatafileentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteTbDatafileent() throws Exception {
        // Initialize the database
        tbDatafileentService.save(tbDatafileent);

        int databaseSizeBeforeDelete = tbDatafileentRepository.findAll().size();

        // Get the tbDatafileent
        restTbDatafileentMockMvc.perform(delete("/api/tb-datafileents/{id}", tbDatafileent.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<TbDatafileent> tbDatafileentList = tbDatafileentRepository.findAll();
        assertThat(tbDatafileentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TbDatafileent.class);
        TbDatafileent tbDatafileent1 = new TbDatafileent();
        tbDatafileent1.setId(1L);
        TbDatafileent tbDatafileent2 = new TbDatafileent();
        tbDatafileent2.setId(tbDatafileent1.getId());
        assertThat(tbDatafileent1).isEqualTo(tbDatafileent2);
        tbDatafileent2.setId(2L);
        assertThat(tbDatafileent1).isNotEqualTo(tbDatafileent2);
        tbDatafileent1.setId(null);
        assertThat(tbDatafileent1).isNotEqualTo(tbDatafileent2);
    }
}
