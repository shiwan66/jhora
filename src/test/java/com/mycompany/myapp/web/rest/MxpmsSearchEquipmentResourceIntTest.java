package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.MxpmsSearchEquipment;
import com.mycompany.myapp.repository.MxpmsSearchEquipmentRepository;
import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;
import com.mycompany.myapp.service.mapper.MxpmsSearchEquipmentMapper;
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
 * Test class for the MxpmsSearchEquipmentResource REST controller.
 *
 * @see MxpmsSearchEquipmentResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class MxpmsSearchEquipmentResourceIntTest {

    private static final String DEFAULT_OBJ_ID = "AAAAAAAAAA";
    private static final String UPDATED_OBJ_ID = "BBBBBBBBBB";

    private static final String DEFAULT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PID = "AAAAAAAAAA";
    private static final String UPDATED_PID = "BBBBBBBBBB";

    private static final String DEFAULT_HASCHILDREN = "AAAAAAAAAA";
    private static final String UPDATED_HASCHILDREN = "BBBBBBBBBB";

    private static final String DEFAULT_IMGURL = "AAAAAAAAAA";
    private static final String UPDATED_IMGURL = "BBBBBBBBBB";

    private static final String DEFAULT_TREEID = "AAAAAAAAAA";
    private static final String UPDATED_TREEID = "BBBBBBBBBB";

    private static final String DEFAULT_EXVALUE = "AAAAAAAAAA";
    private static final String UPDATED_EXVALUE = "BBBBBBBBBB";

    private static final String DEFAULT_NODEMODEL = "AAAAAAAAAA";
    private static final String UPDATED_NODEMODEL = "BBBBBBBBBB";

    private static final String DEFAULT_ITEMTYPE = "AAAAAAAAAA";
    private static final String UPDATED_ITEMTYPE = "BBBBBBBBBB";

    private static final String DEFAULT_ORDERID = "AAAAAAAAAA";
    private static final String UPDATED_ORDERID = "BBBBBBBBBB";

    private static final String DEFAULT_NODEWHBZ = "AAAAAAAAAA";
    private static final String UPDATED_NODEWHBZ = "BBBBBBBBBB";

    private static final String DEFAULT_NODEYXZT = "AAAAAAAAAA";
    private static final String UPDATED_NODEYXZT = "BBBBBBBBBB";

    private static final String DEFAULT_ORGID = "AAAAAAAAAA";
    private static final String UPDATED_ORGID = "BBBBBBBBBB";

    private static final String DEFAULT_BZNAME = "AAAAAAAAAA";
    private static final String UPDATED_BZNAME = "BBBBBBBBBB";

    @Autowired
    private MxpmsSearchEquipmentRepository mxpmsSearchEquipmentRepository;

    @Autowired
    private MxpmsSearchEquipmentMapper mxpmsSearchEquipmentMapper;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMxpmsSearchEquipmentMockMvc;

    private MxpmsSearchEquipment mxpmsSearchEquipment;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MxpmsSearchEquipmentResource mxpmsSearchEquipmentResource = new MxpmsSearchEquipmentResource(mxpmsSearchEquipmentRepository, mxpmsSearchEquipmentMapper);
        this.restMxpmsSearchEquipmentMockMvc = MockMvcBuilders.standaloneSetup(mxpmsSearchEquipmentResource)
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
    public static MxpmsSearchEquipment createEntity(EntityManager em) {
        MxpmsSearchEquipment mxpmsSearchEquipment = new MxpmsSearchEquipment()
            .objId(DEFAULT_OBJ_ID)
            .name(DEFAULT_NAME)
            .pid(DEFAULT_PID)
            .haschildren(DEFAULT_HASCHILDREN)
            .imgurl(DEFAULT_IMGURL)
            .treeid(DEFAULT_TREEID)
            .exvalue(DEFAULT_EXVALUE)
            .nodemodel(DEFAULT_NODEMODEL)
            .itemtype(DEFAULT_ITEMTYPE)
            .orderid(DEFAULT_ORDERID)
            .nodewhbz(DEFAULT_NODEWHBZ)
            .nodeyxzt(DEFAULT_NODEYXZT)
            .orgid(DEFAULT_ORGID)
            .bzname(DEFAULT_BZNAME);
        return mxpmsSearchEquipment;
    }

    @Before
    public void initTest() {
        mxpmsSearchEquipment = createEntity(em);
    }

    @Test
    @Transactional
    public void createMxpmsSearchEquipment() throws Exception {
        int databaseSizeBeforeCreate = mxpmsSearchEquipmentRepository.findAll().size();

        // Create the MxpmsSearchEquipment
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);
        restMxpmsSearchEquipmentMockMvc.perform(post("/api/mxpms-search-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mxpmsSearchEquipmentDTO)))
            .andExpect(status().isCreated());

        // Validate the MxpmsSearchEquipment in the database
        List<MxpmsSearchEquipment> mxpmsSearchEquipmentList = mxpmsSearchEquipmentRepository.findAll();
        assertThat(mxpmsSearchEquipmentList).hasSize(databaseSizeBeforeCreate + 1);
        MxpmsSearchEquipment testMxpmsSearchEquipment = mxpmsSearchEquipmentList.get(mxpmsSearchEquipmentList.size() - 1);
        assertThat(testMxpmsSearchEquipment.getObjId()).isEqualTo(DEFAULT_OBJ_ID);
        assertThat(testMxpmsSearchEquipment.getName()).isEqualTo(DEFAULT_NAME);
        assertThat(testMxpmsSearchEquipment.getPid()).isEqualTo(DEFAULT_PID);
        assertThat(testMxpmsSearchEquipment.getHaschildren()).isEqualTo(DEFAULT_HASCHILDREN);
        assertThat(testMxpmsSearchEquipment.getImgurl()).isEqualTo(DEFAULT_IMGURL);
        assertThat(testMxpmsSearchEquipment.getTreeid()).isEqualTo(DEFAULT_TREEID);
        assertThat(testMxpmsSearchEquipment.getExvalue()).isEqualTo(DEFAULT_EXVALUE);
        assertThat(testMxpmsSearchEquipment.getNodemodel()).isEqualTo(DEFAULT_NODEMODEL);
        assertThat(testMxpmsSearchEquipment.getItemtype()).isEqualTo(DEFAULT_ITEMTYPE);
        assertThat(testMxpmsSearchEquipment.getOrderid()).isEqualTo(DEFAULT_ORDERID);
        assertThat(testMxpmsSearchEquipment.getNodewhbz()).isEqualTo(DEFAULT_NODEWHBZ);
        assertThat(testMxpmsSearchEquipment.getNodeyxzt()).isEqualTo(DEFAULT_NODEYXZT);
        assertThat(testMxpmsSearchEquipment.getOrgid()).isEqualTo(DEFAULT_ORGID);
        assertThat(testMxpmsSearchEquipment.getBzname()).isEqualTo(DEFAULT_BZNAME);
    }

    @Test
    @Transactional
    public void createMxpmsSearchEquipmentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = mxpmsSearchEquipmentRepository.findAll().size();

        // Create the MxpmsSearchEquipment with an existing ID
        mxpmsSearchEquipment.setId(1L);
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMxpmsSearchEquipmentMockMvc.perform(post("/api/mxpms-search-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mxpmsSearchEquipmentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the MxpmsSearchEquipment in the database
        List<MxpmsSearchEquipment> mxpmsSearchEquipmentList = mxpmsSearchEquipmentRepository.findAll();
        assertThat(mxpmsSearchEquipmentList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMxpmsSearchEquipments() throws Exception {
        // Initialize the database
        mxpmsSearchEquipmentRepository.saveAndFlush(mxpmsSearchEquipment);

        // Get all the mxpmsSearchEquipmentList
        restMxpmsSearchEquipmentMockMvc.perform(get("/api/mxpms-search-equipments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mxpmsSearchEquipment.getId().intValue())))
            .andExpect(jsonPath("$.[*].objId").value(hasItem(DEFAULT_OBJ_ID.toString())))
            .andExpect(jsonPath("$.[*].name").value(hasItem(DEFAULT_NAME.toString())))
            .andExpect(jsonPath("$.[*].pid").value(hasItem(DEFAULT_PID.toString())))
            .andExpect(jsonPath("$.[*].haschildren").value(hasItem(DEFAULT_HASCHILDREN.toString())))
            .andExpect(jsonPath("$.[*].imgurl").value(hasItem(DEFAULT_IMGURL.toString())))
            .andExpect(jsonPath("$.[*].treeid").value(hasItem(DEFAULT_TREEID.toString())))
            .andExpect(jsonPath("$.[*].exvalue").value(hasItem(DEFAULT_EXVALUE.toString())))
            .andExpect(jsonPath("$.[*].nodemodel").value(hasItem(DEFAULT_NODEMODEL.toString())))
            .andExpect(jsonPath("$.[*].itemtype").value(hasItem(DEFAULT_ITEMTYPE.toString())))
            .andExpect(jsonPath("$.[*].orderid").value(hasItem(DEFAULT_ORDERID.toString())))
            .andExpect(jsonPath("$.[*].nodewhbz").value(hasItem(DEFAULT_NODEWHBZ.toString())))
            .andExpect(jsonPath("$.[*].nodeyxzt").value(hasItem(DEFAULT_NODEYXZT.toString())))
            .andExpect(jsonPath("$.[*].orgid").value(hasItem(DEFAULT_ORGID.toString())))
            .andExpect(jsonPath("$.[*].bzname").value(hasItem(DEFAULT_BZNAME.toString())));
    }

    @Test
    @Transactional
    public void getMxpmsSearchEquipment() throws Exception {
        // Initialize the database
        mxpmsSearchEquipmentRepository.saveAndFlush(mxpmsSearchEquipment);

        // Get the mxpmsSearchEquipment
        restMxpmsSearchEquipmentMockMvc.perform(get("/api/mxpms-search-equipments/{id}", mxpmsSearchEquipment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mxpmsSearchEquipment.getId().intValue()))
            .andExpect(jsonPath("$.objId").value(DEFAULT_OBJ_ID.toString()))
            .andExpect(jsonPath("$.name").value(DEFAULT_NAME.toString()))
            .andExpect(jsonPath("$.pid").value(DEFAULT_PID.toString()))
            .andExpect(jsonPath("$.haschildren").value(DEFAULT_HASCHILDREN.toString()))
            .andExpect(jsonPath("$.imgurl").value(DEFAULT_IMGURL.toString()))
            .andExpect(jsonPath("$.treeid").value(DEFAULT_TREEID.toString()))
            .andExpect(jsonPath("$.exvalue").value(DEFAULT_EXVALUE.toString()))
            .andExpect(jsonPath("$.nodemodel").value(DEFAULT_NODEMODEL.toString()))
            .andExpect(jsonPath("$.itemtype").value(DEFAULT_ITEMTYPE.toString()))
            .andExpect(jsonPath("$.orderid").value(DEFAULT_ORDERID.toString()))
            .andExpect(jsonPath("$.nodewhbz").value(DEFAULT_NODEWHBZ.toString()))
            .andExpect(jsonPath("$.nodeyxzt").value(DEFAULT_NODEYXZT.toString()))
            .andExpect(jsonPath("$.orgid").value(DEFAULT_ORGID.toString()))
            .andExpect(jsonPath("$.bzname").value(DEFAULT_BZNAME.toString()));
    }

    @Test
    @Transactional
    public void getNonExistingMxpmsSearchEquipment() throws Exception {
        // Get the mxpmsSearchEquipment
        restMxpmsSearchEquipmentMockMvc.perform(get("/api/mxpms-search-equipments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMxpmsSearchEquipment() throws Exception {
        // Initialize the database
        mxpmsSearchEquipmentRepository.saveAndFlush(mxpmsSearchEquipment);
        int databaseSizeBeforeUpdate = mxpmsSearchEquipmentRepository.findAll().size();

        // Update the mxpmsSearchEquipment
        MxpmsSearchEquipment updatedMxpmsSearchEquipment = mxpmsSearchEquipmentRepository.findOne(mxpmsSearchEquipment.getId());
        // Disconnect from session so that the updates on updatedMxpmsSearchEquipment are not directly saved in db
        em.detach(updatedMxpmsSearchEquipment);
        updatedMxpmsSearchEquipment
            .objId(UPDATED_OBJ_ID)
            .name(UPDATED_NAME)
            .pid(UPDATED_PID)
            .haschildren(UPDATED_HASCHILDREN)
            .imgurl(UPDATED_IMGURL)
            .treeid(UPDATED_TREEID)
            .exvalue(UPDATED_EXVALUE)
            .nodemodel(UPDATED_NODEMODEL)
            .itemtype(UPDATED_ITEMTYPE)
            .orderid(UPDATED_ORDERID)
            .nodewhbz(UPDATED_NODEWHBZ)
            .nodeyxzt(UPDATED_NODEYXZT)
            .orgid(UPDATED_ORGID)
            .bzname(UPDATED_BZNAME);
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = mxpmsSearchEquipmentMapper.toDto(updatedMxpmsSearchEquipment);

        restMxpmsSearchEquipmentMockMvc.perform(put("/api/mxpms-search-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mxpmsSearchEquipmentDTO)))
            .andExpect(status().isOk());

        // Validate the MxpmsSearchEquipment in the database
        List<MxpmsSearchEquipment> mxpmsSearchEquipmentList = mxpmsSearchEquipmentRepository.findAll();
        assertThat(mxpmsSearchEquipmentList).hasSize(databaseSizeBeforeUpdate);
        MxpmsSearchEquipment testMxpmsSearchEquipment = mxpmsSearchEquipmentList.get(mxpmsSearchEquipmentList.size() - 1);
        assertThat(testMxpmsSearchEquipment.getObjId()).isEqualTo(UPDATED_OBJ_ID);
        assertThat(testMxpmsSearchEquipment.getName()).isEqualTo(UPDATED_NAME);
        assertThat(testMxpmsSearchEquipment.getPid()).isEqualTo(UPDATED_PID);
        assertThat(testMxpmsSearchEquipment.getHaschildren()).isEqualTo(UPDATED_HASCHILDREN);
        assertThat(testMxpmsSearchEquipment.getImgurl()).isEqualTo(UPDATED_IMGURL);
        assertThat(testMxpmsSearchEquipment.getTreeid()).isEqualTo(UPDATED_TREEID);
        assertThat(testMxpmsSearchEquipment.getExvalue()).isEqualTo(UPDATED_EXVALUE);
        assertThat(testMxpmsSearchEquipment.getNodemodel()).isEqualTo(UPDATED_NODEMODEL);
        assertThat(testMxpmsSearchEquipment.getItemtype()).isEqualTo(UPDATED_ITEMTYPE);
        assertThat(testMxpmsSearchEquipment.getOrderid()).isEqualTo(UPDATED_ORDERID);
        assertThat(testMxpmsSearchEquipment.getNodewhbz()).isEqualTo(UPDATED_NODEWHBZ);
        assertThat(testMxpmsSearchEquipment.getNodeyxzt()).isEqualTo(UPDATED_NODEYXZT);
        assertThat(testMxpmsSearchEquipment.getOrgid()).isEqualTo(UPDATED_ORGID);
        assertThat(testMxpmsSearchEquipment.getBzname()).isEqualTo(UPDATED_BZNAME);
    }

    @Test
    @Transactional
    public void updateNonExistingMxpmsSearchEquipment() throws Exception {
        int databaseSizeBeforeUpdate = mxpmsSearchEquipmentRepository.findAll().size();

        // Create the MxpmsSearchEquipment
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = mxpmsSearchEquipmentMapper.toDto(mxpmsSearchEquipment);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMxpmsSearchEquipmentMockMvc.perform(put("/api/mxpms-search-equipments")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(mxpmsSearchEquipmentDTO)))
            .andExpect(status().isCreated());

        // Validate the MxpmsSearchEquipment in the database
        List<MxpmsSearchEquipment> mxpmsSearchEquipmentList = mxpmsSearchEquipmentRepository.findAll();
        assertThat(mxpmsSearchEquipmentList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMxpmsSearchEquipment() throws Exception {
        // Initialize the database
        mxpmsSearchEquipmentRepository.saveAndFlush(mxpmsSearchEquipment);
        int databaseSizeBeforeDelete = mxpmsSearchEquipmentRepository.findAll().size();

        // Get the mxpmsSearchEquipment
        restMxpmsSearchEquipmentMockMvc.perform(delete("/api/mxpms-search-equipments/{id}", mxpmsSearchEquipment.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<MxpmsSearchEquipment> mxpmsSearchEquipmentList = mxpmsSearchEquipmentRepository.findAll();
        assertThat(mxpmsSearchEquipmentList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(MxpmsSearchEquipment.class);
        MxpmsSearchEquipment mxpmsSearchEquipment1 = new MxpmsSearchEquipment();
        mxpmsSearchEquipment1.setId(1L);
        MxpmsSearchEquipment mxpmsSearchEquipment2 = new MxpmsSearchEquipment();
        mxpmsSearchEquipment2.setId(mxpmsSearchEquipment1.getId());
        assertThat(mxpmsSearchEquipment1).isEqualTo(mxpmsSearchEquipment2);
        mxpmsSearchEquipment2.setId(2L);
        assertThat(mxpmsSearchEquipment1).isNotEqualTo(mxpmsSearchEquipment2);
        mxpmsSearchEquipment1.setId(null);
        assertThat(mxpmsSearchEquipment1).isNotEqualTo(mxpmsSearchEquipment2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MxpmsSearchEquipmentDTO.class);
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO1 = new MxpmsSearchEquipmentDTO();
        mxpmsSearchEquipmentDTO1.setId(1L);
        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO2 = new MxpmsSearchEquipmentDTO();
        assertThat(mxpmsSearchEquipmentDTO1).isNotEqualTo(mxpmsSearchEquipmentDTO2);
        mxpmsSearchEquipmentDTO2.setId(mxpmsSearchEquipmentDTO1.getId());
        assertThat(mxpmsSearchEquipmentDTO1).isEqualTo(mxpmsSearchEquipmentDTO2);
        mxpmsSearchEquipmentDTO2.setId(2L);
        assertThat(mxpmsSearchEquipmentDTO1).isNotEqualTo(mxpmsSearchEquipmentDTO2);
        mxpmsSearchEquipmentDTO1.setId(null);
        assertThat(mxpmsSearchEquipmentDTO1).isNotEqualTo(mxpmsSearchEquipmentDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(mxpmsSearchEquipmentMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(mxpmsSearchEquipmentMapper.fromId(null)).isNull();
    }
}
