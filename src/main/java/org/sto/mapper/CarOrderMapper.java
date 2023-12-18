package org.sto.mapper;

import org.sto.dto.CarOrderDTO;
import org.sto.dto.UserChatDTO;
import org.sto.entity.CarOrder;
import org.sto.entity.enumerable.Status;

public class CarOrderMapper {
    public static CarOrder fromDTOToEntity(final CarOrderDTO carOrderDTO) {
        return CarOrder.builder()
                .car(carOrderDTO.getCar())
                .startDate(carOrderDTO.getStartDate())
                .user(carOrderDTO.getUser())
                .status(Status.IN_PROCESS)
                .build();
    }
   /* public static CarOrderDTO fromEntityToDTO(final CarOrder carOrder) {
        return CarOrderDTO.builder().car(carOrder.getCar())
                .user(carOrder.getUser())
                .startDate(carOrder.getStartDate())
                .build();
    }*/
}
