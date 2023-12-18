package org.sto.mapper;

import org.sto.dto.CarPartDTO;
import org.sto.entity.CarPart;

public class CarPartMapper {


    public static CarPart fromDTOToEntity(final CarPartDTO carPartDTO) {
        return CarPart.builder().code(carPartDTO.getCode())
                .title(carPartDTO.getTitle())
                .price(carPartDTO.getPrice())
                .build();
    }
}
