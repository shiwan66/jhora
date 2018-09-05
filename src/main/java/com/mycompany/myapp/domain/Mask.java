package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * 掩模明细.
 */
@ApiModel(description = "掩模明细.")
@Entity
@Table(name = "mask")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Mask implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "ch")
    private String ch;

    @Column(name = "dybh")
    private String dybh;

    @Column(name = "sjqhb")
    private String sjqhb;

    @Column(name = "sj_cd")
    private String sjCD;

    @Column(name = "zs")
    private Long zs;

    @Column(name = "crtxbh_1")
    private String crtxbh1;

    @Column(name = "crtxbh_2")
    private String crtxbh2;

    @Column(name = "cdcstxwhiteblack")
    private String cdcstxwhiteblack;

    @Column(name = "cdcstxmbcc")
    private Long cdcstxmbcc;

    @Column(name = "cdcstxgc")
    private Long cdcstxgc;

    @Column(name = "qxdx")
    private Long qxdx;

    @Column(name = "qxmd")
    private Long qxmd;

    @Column(name = "tzjd")
    private Long tzjd;

    @Column(name = "remark")
    private String remark;

    @Column(name = "aaa")
    private Long aaa;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCh() {
        return ch;
    }

    public Mask ch(String ch) {
        this.ch = ch;
        return this;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getDybh() {
        return dybh;
    }

    public Mask dybh(String dybh) {
        this.dybh = dybh;
        return this;
    }

    public void setDybh(String dybh) {
        this.dybh = dybh;
    }

    public String getSjqhb() {
        return sjqhb;
    }

    public Mask sjqhb(String sjqhb) {
        this.sjqhb = sjqhb;
        return this;
    }

    public void setSjqhb(String sjqhb) {
        this.sjqhb = sjqhb;
    }

    public String getSjCD() {
        return sjCD;
    }

    public Mask sjCD(String sjCD) {
        this.sjCD = sjCD;
        return this;
    }

    public void setSjCD(String sjCD) {
        this.sjCD = sjCD;
    }

    public Long getZs() {
        return zs;
    }

    public Mask zs(Long zs) {
        this.zs = zs;
        return this;
    }

    public void setZs(Long zs) {
        this.zs = zs;
    }

    public String getCrtxbh1() {
        return crtxbh1;
    }

    public Mask crtxbh1(String crtxbh1) {
        this.crtxbh1 = crtxbh1;
        return this;
    }

    public void setCrtxbh1(String crtxbh1) {
        this.crtxbh1 = crtxbh1;
    }

    public String getCrtxbh2() {
        return crtxbh2;
    }

    public Mask crtxbh2(String crtxbh2) {
        this.crtxbh2 = crtxbh2;
        return this;
    }

    public void setCrtxbh2(String crtxbh2) {
        this.crtxbh2 = crtxbh2;
    }

    public String getCdcstxwhiteblack() {
        return cdcstxwhiteblack;
    }

    public Mask cdcstxwhiteblack(String cdcstxwhiteblack) {
        this.cdcstxwhiteblack = cdcstxwhiteblack;
        return this;
    }

    public void setCdcstxwhiteblack(String cdcstxwhiteblack) {
        this.cdcstxwhiteblack = cdcstxwhiteblack;
    }

    public Long getCdcstxmbcc() {
        return cdcstxmbcc;
    }

    public Mask cdcstxmbcc(Long cdcstxmbcc) {
        this.cdcstxmbcc = cdcstxmbcc;
        return this;
    }

    public void setCdcstxmbcc(Long cdcstxmbcc) {
        this.cdcstxmbcc = cdcstxmbcc;
    }

    public Long getCdcstxgc() {
        return cdcstxgc;
    }

    public Mask cdcstxgc(Long cdcstxgc) {
        this.cdcstxgc = cdcstxgc;
        return this;
    }

    public void setCdcstxgc(Long cdcstxgc) {
        this.cdcstxgc = cdcstxgc;
    }

    public Long getQxdx() {
        return qxdx;
    }

    public Mask qxdx(Long qxdx) {
        this.qxdx = qxdx;
        return this;
    }

    public void setQxdx(Long qxdx) {
        this.qxdx = qxdx;
    }

    public Long getQxmd() {
        return qxmd;
    }

    public Mask qxmd(Long qxmd) {
        this.qxmd = qxmd;
        return this;
    }

    public void setQxmd(Long qxmd) {
        this.qxmd = qxmd;
    }

    public Long getTzjd() {
        return tzjd;
    }

    public Mask tzjd(Long tzjd) {
        this.tzjd = tzjd;
        return this;
    }

    public void setTzjd(Long tzjd) {
        this.tzjd = tzjd;
    }

    public String getRemark() {
        return remark;
    }

    public Mask remark(String remark) {
        this.remark = remark;
        return this;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAaa() {
        return aaa;
    }

    public Mask aaa(Long aaa) {
        this.aaa = aaa;
        return this;
    }

    public void setAaa(Long aaa) {
        this.aaa = aaa;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Mask mask = (Mask) o;
        if (mask.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mask.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Mask{" +
            "id=" + getId() +
            ", ch='" + getCh() + "'" +
            ", dybh='" + getDybh() + "'" +
            ", sjqhb='" + getSjqhb() + "'" +
            ", sjCD='" + getSjCD() + "'" +
            ", zs=" + getZs() +
            ", crtxbh1='" + getCrtxbh1() + "'" +
            ", crtxbh2='" + getCrtxbh2() + "'" +
            ", cdcstxwhiteblack='" + getCdcstxwhiteblack() + "'" +
            ", cdcstxmbcc=" + getCdcstxmbcc() +
            ", cdcstxgc=" + getCdcstxgc() +
            ", qxdx=" + getQxdx() +
            ", qxmd=" + getQxmd() +
            ", tzjd=" + getTzjd() +
            ", remark='" + getRemark() + "'" +
            ", aaa=" + getAaa() +
            "}";
    }
}
