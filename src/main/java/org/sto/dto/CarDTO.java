package org.sto.dto;

import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sto.entity.CarOrder;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarDTO {
    private String brand;
    private String model;
    private int year;
    private String vin;

}
