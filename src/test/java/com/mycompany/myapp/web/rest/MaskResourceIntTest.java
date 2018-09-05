package com.mycompany.myapp.web.rest;

import com.mycompany.myapp.JhoraApp;

import com.mycompany.myapp.domain.Mask;
import com.mycompany.myapp.repository.MaskRepository;
import com.mycompany.myapp.service.MaskService;
import com.mycompany.myapp.service.dto.MaskDTO;
import com.mycompany.myapp.service.mapper.MaskMapper;
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
 * Test class for the MaskResource REST controller.
 *
 * @see MaskResource
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JhoraApp.class)
public class MaskResourceIntTest {

    private static final String DEFAULT_CH = "AAAAAAAAAA";
    private static final String UPDATED_CH = "BBBBBBBBBB";

    private static final String DEFAULT_DYBH = "AAAAAAAAAA";
    private static final String UPDATED_DYBH = "BBBBBBBBBB";

    private static final String DEFAULT_SJQHB = "AAAAAAAAAA";
    private static final String UPDATED_SJQHB = "BBBBBBBBBB";

    private static final String DEFAULT_SJ_CD = "AAAAAAAAAA";
    private static final String UPDATED_SJ_CD = "BBBBBBBBBB";

    private static final Long DEFAULT_ZS = 1L;
    private static final Long UPDATED_ZS = 2L;

    private static final String DEFAULT_CRTXBH_1 = "AAAAAAAAAA";
    private static final String UPDATED_CRTXBH_1 = "BBBBBBBBBB";

    private static final String DEFAULT_CRTXBH_2 = "AAAAAAAAAA";
    private static final String UPDATED_CRTXBH_2 = "BBBBBBBBBB";

    private static final String DEFAULT_CDCSTXWHITEBLACK = "AAAAAAAAAA";
    private static final String UPDATED_CDCSTXWHITEBLACK = "BBBBBBBBBB";

    private static final Long DEFAULT_CDCSTXMBCC = 1L;
    private static final Long UPDATED_CDCSTXMBCC = 2L;

    private static final Long DEFAULT_CDCSTXGC = 1L;
    private static final Long UPDATED_CDCSTXGC = 2L;

    private static final Long DEFAULT_QXDX = 1L;
    private static final Long UPDATED_QXDX = 2L;

    private static final Long DEFAULT_QXMD = 1L;
    private static final Long UPDATED_QXMD = 2L;

    private static final Long DEFAULT_TZJD = 1L;
    private static final Long UPDATED_TZJD = 2L;

    private static final String DEFAULT_REMARK = "AAAAAAAAAA";
    private static final String UPDATED_REMARK = "BBBBBBBBBB";

    private static final Long DEFAULT_AAA = 1L;
    private static final Long UPDATED_AAA = 2L;

    @Autowired
    private MaskRepository maskRepository;

    @Autowired
    private MaskMapper maskMapper;

    @Autowired
    private MaskService maskService;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    private MockMvc restMaskMockMvc;

    private Mask mask;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final MaskResource maskResource = new MaskResource(maskService);
        this.restMaskMockMvc = MockMvcBuilders.standaloneSetup(maskResource)
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
    public static Mask createEntity(EntityManager em) {
        Mask mask = new Mask()
            .ch(DEFAULT_CH)
            .dybh(DEFAULT_DYBH)
            .sjqhb(DEFAULT_SJQHB)
            .sjCD(DEFAULT_SJ_CD)
            .zs(DEFAULT_ZS)
            .crtxbh1(DEFAULT_CRTXBH_1)
            .crtxbh2(DEFAULT_CRTXBH_2)
            .cdcstxwhiteblack(DEFAULT_CDCSTXWHITEBLACK)
            .cdcstxmbcc(DEFAULT_CDCSTXMBCC)
            .cdcstxgc(DEFAULT_CDCSTXGC)
            .qxdx(DEFAULT_QXDX)
            .qxmd(DEFAULT_QXMD)
            .tzjd(DEFAULT_TZJD)
            .remark(DEFAULT_REMARK)
            .aaa(DEFAULT_AAA);
        return mask;
    }

    @Before
    public void initTest() {
        mask = createEntity(em);
    }

    @Test
    @Transactional
    public void createMask() throws Exception {
        int databaseSizeBeforeCreate = maskRepository.findAll().size();

        // Create the Mask
        MaskDTO maskDTO = maskMapper.toDto(mask);
        restMaskMockMvc.perform(post("/api/masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maskDTO)))
            .andExpect(status().isCreated());

        // Validate the Mask in the database
        List<Mask> maskList = maskRepository.findAll();
        assertThat(maskList).hasSize(databaseSizeBeforeCreate + 1);
        Mask testMask = maskList.get(maskList.size() - 1);
        assertThat(testMask.getCh()).isEqualTo(DEFAULT_CH);
        assertThat(testMask.getDybh()).isEqualTo(DEFAULT_DYBH);
        assertThat(testMask.getSjqhb()).isEqualTo(DEFAULT_SJQHB);
        assertThat(testMask.getSjCD()).isEqualTo(DEFAULT_SJ_CD);
        assertThat(testMask.getZs()).isEqualTo(DEFAULT_ZS);
        assertThat(testMask.getCrtxbh1()).isEqualTo(DEFAULT_CRTXBH_1);
        assertThat(testMask.getCrtxbh2()).isEqualTo(DEFAULT_CRTXBH_2);
        assertThat(testMask.getCdcstxwhiteblack()).isEqualTo(DEFAULT_CDCSTXWHITEBLACK);
        assertThat(testMask.getCdcstxmbcc()).isEqualTo(DEFAULT_CDCSTXMBCC);
        assertThat(testMask.getCdcstxgc()).isEqualTo(DEFAULT_CDCSTXGC);
        assertThat(testMask.getQxdx()).isEqualTo(DEFAULT_QXDX);
        assertThat(testMask.getQxmd()).isEqualTo(DEFAULT_QXMD);
        assertThat(testMask.getTzjd()).isEqualTo(DEFAULT_TZJD);
        assertThat(testMask.getRemark()).isEqualTo(DEFAULT_REMARK);
        assertThat(testMask.getAaa()).isEqualTo(DEFAULT_AAA);
    }

    @Test
    @Transactional
    public void createMaskWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = maskRepository.findAll().size();

        // Create the Mask with an existing ID
        mask.setId(1L);
        MaskDTO maskDTO = maskMapper.toDto(mask);

        // An entity with an existing ID cannot be created, so this API call must fail
        restMaskMockMvc.perform(post("/api/masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maskDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Mask in the database
        List<Mask> maskList = maskRepository.findAll();
        assertThat(maskList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    public void getAllMasks() throws Exception {
        // Initialize the database
        maskRepository.saveAndFlush(mask);

        // Get all the maskList
        restMaskMockMvc.perform(get("/api/masks?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(mask.getId().intValue())))
            .andExpect(jsonPath("$.[*].ch").value(hasItem(DEFAULT_CH.toString())))
            .andExpect(jsonPath("$.[*].dybh").value(hasItem(DEFAULT_DYBH.toString())))
            .andExpect(jsonPath("$.[*].sjqhb").value(hasItem(DEFAULT_SJQHB.toString())))
            .andExpect(jsonPath("$.[*].sjCD").value(hasItem(DEFAULT_SJ_CD.toString())))
            .andExpect(jsonPath("$.[*].zs").value(hasItem(DEFAULT_ZS.intValue())))
            .andExpect(jsonPath("$.[*].crtxbh1").value(hasItem(DEFAULT_CRTXBH_1.toString())))
            .andExpect(jsonPath("$.[*].crtxbh2").value(hasItem(DEFAULT_CRTXBH_2.toString())))
            .andExpect(jsonPath("$.[*].cdcstxwhiteblack").value(hasItem(DEFAULT_CDCSTXWHITEBLACK.toString())))
            .andExpect(jsonPath("$.[*].cdcstxmbcc").value(hasItem(DEFAULT_CDCSTXMBCC.intValue())))
            .andExpect(jsonPath("$.[*].cdcstxgc").value(hasItem(DEFAULT_CDCSTXGC.intValue())))
            .andExpect(jsonPath("$.[*].qxdx").value(hasItem(DEFAULT_QXDX.intValue())))
            .andExpect(jsonPath("$.[*].qxmd").value(hasItem(DEFAULT_QXMD.intValue())))
            .andExpect(jsonPath("$.[*].tzjd").value(hasItem(DEFAULT_TZJD.intValue())))
            .andExpect(jsonPath("$.[*].remark").value(hasItem(DEFAULT_REMARK.toString())))
            .andExpect(jsonPath("$.[*].aaa").value(hasItem(DEFAULT_AAA.intValue())));
    }

    @Test
    @Transactional
    public void getMask() throws Exception {
        // Initialize the database
        maskRepository.saveAndFlush(mask);

        // Get the mask
        restMaskMockMvc.perform(get("/api/masks/{id}", mask.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
            .andExpect(jsonPath("$.id").value(mask.getId().intValue()))
            .andExpect(jsonPath("$.ch").value(DEFAULT_CH.toString()))
            .andExpect(jsonPath("$.dybh").value(DEFAULT_DYBH.toString()))
            .andExpect(jsonPath("$.sjqhb").value(DEFAULT_SJQHB.toString()))
            .andExpect(jsonPath("$.sjCD").value(DEFAULT_SJ_CD.toString()))
            .andExpect(jsonPath("$.zs").value(DEFAULT_ZS.intValue()))
            .andExpect(jsonPath("$.crtxbh1").value(DEFAULT_CRTXBH_1.toString()))
            .andExpect(jsonPath("$.crtxbh2").value(DEFAULT_CRTXBH_2.toString()))
            .andExpect(jsonPath("$.cdcstxwhiteblack").value(DEFAULT_CDCSTXWHITEBLACK.toString()))
            .andExpect(jsonPath("$.cdcstxmbcc").value(DEFAULT_CDCSTXMBCC.intValue()))
            .andExpect(jsonPath("$.cdcstxgc").value(DEFAULT_CDCSTXGC.intValue()))
            .andExpect(jsonPath("$.qxdx").value(DEFAULT_QXDX.intValue()))
            .andExpect(jsonPath("$.qxmd").value(DEFAULT_QXMD.intValue()))
            .andExpect(jsonPath("$.tzjd").value(DEFAULT_TZJD.intValue()))
            .andExpect(jsonPath("$.remark").value(DEFAULT_REMARK.toString()))
            .andExpect(jsonPath("$.aaa").value(DEFAULT_AAA.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingMask() throws Exception {
        // Get the mask
        restMaskMockMvc.perform(get("/api/masks/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateMask() throws Exception {
        // Initialize the database
        maskRepository.saveAndFlush(mask);
        int databaseSizeBeforeUpdate = maskRepository.findAll().size();

        // Update the mask
        Mask updatedMask = maskRepository.findOne(mask.getId());
        // Disconnect from session so that the updates on updatedMask are not directly saved in db
        em.detach(updatedMask);
        updatedMask
            .ch(UPDATED_CH)
            .dybh(UPDATED_DYBH)
            .sjqhb(UPDATED_SJQHB)
            .sjCD(UPDATED_SJ_CD)
            .zs(UPDATED_ZS)
            .crtxbh1(UPDATED_CRTXBH_1)
            .crtxbh2(UPDATED_CRTXBH_2)
            .cdcstxwhiteblack(UPDATED_CDCSTXWHITEBLACK)
            .cdcstxmbcc(UPDATED_CDCSTXMBCC)
            .cdcstxgc(UPDATED_CDCSTXGC)
            .qxdx(UPDATED_QXDX)
            .qxmd(UPDATED_QXMD)
            .tzjd(UPDATED_TZJD)
            .remark(UPDATED_REMARK)
            .aaa(UPDATED_AAA);
        MaskDTO maskDTO = maskMapper.toDto(updatedMask);

        restMaskMockMvc.perform(put("/api/masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maskDTO)))
            .andExpect(status().isOk());

        // Validate the Mask in the database
        List<Mask> maskList = maskRepository.findAll();
        assertThat(maskList).hasSize(databaseSizeBeforeUpdate);
        Mask testMask = maskList.get(maskList.size() - 1);
        assertThat(testMask.getCh()).isEqualTo(UPDATED_CH);
        assertThat(testMask.getDybh()).isEqualTo(UPDATED_DYBH);
        assertThat(testMask.getSjqhb()).isEqualTo(UPDATED_SJQHB);
        assertThat(testMask.getSjCD()).isEqualTo(UPDATED_SJ_CD);
        assertThat(testMask.getZs()).isEqualTo(UPDATED_ZS);
        assertThat(testMask.getCrtxbh1()).isEqualTo(UPDATED_CRTXBH_1);
        assertThat(testMask.getCrtxbh2()).isEqualTo(UPDATED_CRTXBH_2);
        assertThat(testMask.getCdcstxwhiteblack()).isEqualTo(UPDATED_CDCSTXWHITEBLACK);
        assertThat(testMask.getCdcstxmbcc()).isEqualTo(UPDATED_CDCSTXMBCC);
        assertThat(testMask.getCdcstxgc()).isEqualTo(UPDATED_CDCSTXGC);
        assertThat(testMask.getQxdx()).isEqualTo(UPDATED_QXDX);
        assertThat(testMask.getQxmd()).isEqualTo(UPDATED_QXMD);
        assertThat(testMask.getTzjd()).isEqualTo(UPDATED_TZJD);
        assertThat(testMask.getRemark()).isEqualTo(UPDATED_REMARK);
        assertThat(testMask.getAaa()).isEqualTo(UPDATED_AAA);
    }

    @Test
    @Transactional
    public void updateNonExistingMask() throws Exception {
        int databaseSizeBeforeUpdate = maskRepository.findAll().size();

        // Create the Mask
        MaskDTO maskDTO = maskMapper.toDto(mask);

        // If the entity doesn't have an ID, it will be created instead of just being updated
        restMaskMockMvc.perform(put("/api/masks")
            .contentType(TestUtil.APPLICATION_JSON_UTF8)
            .content(TestUtil.convertObjectToJsonBytes(maskDTO)))
            .andExpect(status().isCreated());

        // Validate the Mask in the database
        List<Mask> maskList = maskRepository.findAll();
        assertThat(maskList).hasSize(databaseSizeBeforeUpdate + 1);
    }

    @Test
    @Transactional
    public void deleteMask() throws Exception {
        // Initialize the database
        maskRepository.saveAndFlush(mask);
        int databaseSizeBeforeDelete = maskRepository.findAll().size();

        // Get the mask
        restMaskMockMvc.perform(delete("/api/masks/{id}", mask.getId())
            .accept(TestUtil.APPLICATION_JSON_UTF8))
            .andExpect(status().isOk());

        // Validate the database is empty
        List<Mask> maskList = maskRepository.findAll();
        assertThat(maskList).hasSize(databaseSizeBeforeDelete - 1);
    }

    @Test
    @Transactional
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Mask.class);
        Mask mask1 = new Mask();
        mask1.setId(1L);
        Mask mask2 = new Mask();
        mask2.setId(mask1.getId());
        assertThat(mask1).isEqualTo(mask2);
        mask2.setId(2L);
        assertThat(mask1).isNotEqualTo(mask2);
        mask1.setId(null);
        assertThat(mask1).isNotEqualTo(mask2);
    }

    @Test
    @Transactional
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(MaskDTO.class);
        MaskDTO maskDTO1 = new MaskDTO();
        maskDTO1.setId(1L);
        MaskDTO maskDTO2 = new MaskDTO();
        assertThat(maskDTO1).isNotEqualTo(maskDTO2);
        maskDTO2.setId(maskDTO1.getId());
        assertThat(maskDTO1).isEqualTo(maskDTO2);
        maskDTO2.setId(2L);
        assertThat(maskDTO1).isNotEqualTo(maskDTO2);
        maskDTO1.setId(null);
        assertThat(maskDTO1).isNotEqualTo(maskDTO2);
    }

    @Test
    @Transactional
    public void testEntityFromId() {
        assertThat(maskMapper.fromId(42L).getId()).isEqualTo(42);
        assertThat(maskMapper.fromId(null)).isNull();
    }
}
