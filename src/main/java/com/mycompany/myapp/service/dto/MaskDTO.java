package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Mask entity.
 */
public class MaskDTO implements Serializable {

    private Long id;

    private String ch;

    private String dybh;

    private String sjqhb;

    private String sjcd;

    private Long zs;

    private String crtxbh1;

    private String crtxbh2;

    private String cdcstxwhiteblack;

    private Long cdcstxmbcc;

    private Long cdcstxgc;

    private Long qxdx;

    private Long qxmd;

    private Long tzjd;

    private String remark;

    private Long aaa;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public String getDybh() {
        return dybh;
    }

    public void setDybh(String dybh) {
        this.dybh = dybh;
    }

    public String getSjqhb() {
        return sjqhb;
    }

    public void setSjqhb(String sjqhb) {
        this.sjqhb = sjqhb;
    }

    public String getSjcd() {
        return sjcd;
    }

    public void setSjcd(String sjcd) {
        this.sjcd = sjcd;
    }

    public Long getZs() {
        return zs;
    }

    public void setZs(Long zs) {
        this.zs = zs;
    }

    public String getCrtxbh1() {
        return crtxbh1;
    }

    public void setCrtxbh1(String crtxbh1) {
        this.crtxbh1 = crtxbh1;
    }

    public String getCrtxbh2() {
        return crtxbh2;
    }

    public void setCrtxbh2(String crtxbh2) {
        this.crtxbh2 = crtxbh2;
    }

    public String getCdcstxwhiteblack() {
        return cdcstxwhiteblack;
    }

    public void setCdcstxwhiteblack(String cdcstxwhiteblack) {
        this.cdcstxwhiteblack = cdcstxwhiteblack;
    }

    public Long getCdcstxmbcc() {
        return cdcstxmbcc;
    }

    public void setCdcstxmbcc(Long cdcstxmbcc) {
        this.cdcstxmbcc = cdcstxmbcc;
    }

    public Long getCdcstxgc() {
        return cdcstxgc;
    }

    public void setCdcstxgc(Long cdcstxgc) {
        this.cdcstxgc = cdcstxgc;
    }

    public Long getQxdx() {
        return qxdx;
    }

    public void setQxdx(Long qxdx) {
        this.qxdx = qxdx;
    }

    public Long getQxmd() {
        return qxmd;
    }

    public void setQxmd(Long qxmd) {
        this.qxmd = qxmd;
    }

    public Long getTzjd() {
        return tzjd;
    }

    public void setTzjd(Long tzjd) {
        this.tzjd = tzjd;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getAaa() {
        return aaa;
    }

    public void setAaa(Long aaa) {
        this.aaa = aaa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MaskDTO maskDTO = (MaskDTO) o;
        if(maskDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), maskDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MaskDTO{" +
            "id=" + getId() +
            ", ch='" + getCh() + "'" +
            ", dybh='" + getDybh() + "'" +
            ", sjqhb='" + getSjqhb() + "'" +
            ", sjcd='" + getSjcd() + "'" +
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
