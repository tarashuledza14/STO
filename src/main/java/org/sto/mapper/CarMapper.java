package org.sto.mapper;

import org.sto.dto.CarDTO;
import org.sto.entity.Car;

public class CarMapper {
    public static Car fromDTOToEntity(final CarDTO carDto) {
        return Car.builder().brand(carDto.getBrand())
                .model(carDto.getModel())
                .vin(carDto.getVin())
                .year(carDto.getYear())
                .build();
    }
}
