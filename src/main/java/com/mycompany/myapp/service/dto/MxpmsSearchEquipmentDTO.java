package com.mycompany.myapp.service.dto;


import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the MxpmsSearchEquipment entity.
 */
public class MxpmsSearchEquipmentDTO implements Serializable {

    private Long id;

    private String objId;

    private String name;

    private String pid;

    private String haschildren;

    private String imgurl;

    private String treeid;

    private String exvalue;

    private String nodemodel;

    private String itemtype;

    private String orderid;

    private String nodewhbz;

    private String nodeyxzt;

    private String orgid;

    private String bzname;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjId() {
        return objId;
    }

    public void setObjId(String objId) {
        this.objId = objId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getHaschildren() {
        return haschildren;
    }

    public void setHaschildren(String haschildren) {
        this.haschildren = haschildren;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTreeid() {
        return treeid;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    public String getExvalue() {
        return exvalue;
    }

    public void setExvalue(String exvalue) {
        this.exvalue = exvalue;
    }

    public String getNodemodel() {
        return nodemodel;
    }

    public void setNodemodel(String nodemodel) {
        this.nodemodel = nodemodel;
    }

    public String getItemtype() {
        return itemtype;
    }

    public void setItemtype(String itemtype) {
        this.itemtype = itemtype;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getNodewhbz() {
        return nodewhbz;
    }

    public void setNodewhbz(String nodewhbz) {
        this.nodewhbz = nodewhbz;
    }

    public String getNodeyxzt() {
        return nodeyxzt;
    }

    public void setNodeyxzt(String nodeyxzt) {
        this.nodeyxzt = nodeyxzt;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public String getBzname() {
        return bzname;
    }

    public void setBzname(String bzname) {
        this.bzname = bzname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        MxpmsSearchEquipmentDTO mxpmsSearchEquipmentDTO = (MxpmsSearchEquipmentDTO) o;
        if(mxpmsSearchEquipmentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), mxpmsSearchEquipmentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MxpmsSearchEquipmentDTO{" +
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
