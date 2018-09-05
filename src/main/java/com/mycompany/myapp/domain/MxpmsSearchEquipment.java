package com.mycompany.myapp.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * The equipemnt entity.
 */
@ApiModel(description = "The equipemnt entity.")
@Entity
@Table(name = "mxpms_search_equipment")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MxpmsSearchEquipment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    /**
     * The firstname attribute.
     */
    @ApiModelProperty(value = "The firstname attribute.")
    @Column(name = "obj_id")
    private String objId;

    @Column(name = "name")
    private String name;

    @Column(name = "pid")
    private String pid;

    @Column(name = "haschildren")
    private String haschildren;

    @Column(name = "imgurl")
    private String imgurl;

    @Column(name = "treeid")
    private String treeid;

    @Column(name = "exvalue")
    private String exvalue;

    @Column(name = "nodemodel")
    private String nodemodel;

    @Column(name = "itemtype")
    private String itemtype;

    @Column(name = "orderid")
    private String orderid;

    @Column(name = "nodewhbz")
    private String nodewhbz;

    @Column(name = "nodeyxzt")
    private String nodeyxzt;

    @Column(name = "orgid")
    private String orgid;

    @Column(name = "bzname")
    private String bzname;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjId() {
        return objId;
    }

    public MxpmsSearchEquipment objId(String objId) {
        this.objId = objId;
        return this;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getName() {
        return name;
    }

    public MxpmsSearchEquipment name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public MxpmsSearchEquipment pid(String pid) {
        this.pid = pid;
        return this;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getHaschildren() {
        return haschildren;
    }

    public MxpmsSearchEquipment haschildren(String haschildren) {
        this.haschildren = haschildren;
        return this;
    }

    public void setHaschildren(String haschildren) {
        this.haschildren = haschildren;
    }

    public String getImgurl() {
        return imgurl;
    }

    public MxpmsSearchEquipment imgurl(String imgurl) {
        this.imgurl = imgurl;
        return this;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTreeid() {
        return treeid;
    }

    public MxpmsSearchEquipment treeid(String treeid) {
        this.treeid = treeid;
        return this;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    public String getExvalue() {
        return exvalue;
    }

    public MxpmsSearchEquipment exvalue(String exvalue) {
        this.exvalue = exvalue;
        return this;
    }

    public void setExvalue(String exvalue) {
        this.exvalue = exvalue;
    }

    public String getNodemodel() {
        return nodemodel;
    }

    public MxpmsSearchEquipment nodemodel(String nodemodel) {
        this.nodemodel = nodemodel;
        return this;
    }

    public void setNodemodel(String nodemodel) {
        this.nodemodel = nodemodel;
    }

    public String getItemtype() {
        return itemtype;
    }

    public MxpmsSearchEquipment itemtype(String itemtype) {
        this.itemtype = itemtype;
        return this;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getOrderid() {
        return orderid;
    }

    public MxpmsSearchEquipment orderid(String orderid) {
        this.orderid = orderid;
        return this;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getNodewhbz() {
        return nodewhbz;
    }

    public MxpmsSearchEquipment nodewhbz(String nodewhbz) {
        this.nodewhbz = nodewhbz;
        return this;
    }

    public void setNodewhbz(String nodewhbz) {
        this.nodewhbz = nodewhbz;
    }

    public String getNodeyxzt() {
        return nodeyxzt;
    }

    public MxpmsSearchEquipment nodeyxzt(String nodeyxzt) {
        this.nodeyxzt = nodeyxzt;
        return this;
    }

    public void setNodeyxzt(String nodeyxzt) {
        this.nodeyxzt = nodeyxzt;
    }

    public String getOrgid() {
        return orgid;
    }

    public MxpmsSearchEquipment orgid(String orgid) {
        this.orgid = orgid;
        return this;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getBzname() {
        return bzname;
    }

    public MxpmsSearchEquipment bzname(String bzname) {
        this.bzname = bzname;
        return this;
    }

    public void setBzname(String bzname) {
        this.bzname = bzname;
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
        MxpmsSearchEquipment mxpmsSearchEquipment = (MxpmsSearchEquipment) o;
        if (mxpmsSearchEquipment.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mxpmsSearchEquipment.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MxpmsSearchEquipment{" +
            "id=" + getId() +
            ", objId='" + getObjId() + "'" +
            ", name='" + getName() + "'" +
            ", pid='" + getPid() + "'" +
            ", haschildren='" + getHaschildren() + "'" +
            ", imgurl='" + getImgurl() + "'" +
            ", treeid='" + getTreeid() + "'" +
            ", exvalue='" + getExvalue() + "'" +
            ", nodemodel='" + getNodemodel() + "'" +
            ", itemtype='" + getItemtype() + "'" +
            ", orderid='" + getOrderid() + "'" +
            ", nodewhbz='" + getNodewhbz() + "'" +
            ", nodeyxzt='" + getNodeyxzt() + "'" +
            ", orgid='" + getOrgid() + "'" +
            ", bzname='" + getBzname() + "'" +
            "}";
    }
}
