package com.mycompany.myapp.service.mapper;

import com.mycompany.myapp.domain.*;
import com.mycompany.myapp.service.dto.MxpmsSearchEquipmentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity MxpmsSearchEquipment and its DTO MxpmsSearchEquipmentDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface MxpmsSearchEquipmentMapper extends EntityMapper<MxpmsSearchEquipmentDTO, MxpmsSearchEquipment> {



    default MxpmsSearchEquipment fromId(Long id) {
        if (id == null) {
            return null;
        }
        MxpmsSearchEquipment mxpmsSearchEquipment = new MxpmsSearchEquipment();
        mxpmsSearchEquipment.setId(id);
        return mxpmsSearchEquipment;
    }
}
