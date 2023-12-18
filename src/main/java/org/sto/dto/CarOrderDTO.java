package org.sto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.sto.entity.Car;
import org.sto.entity.User;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarOrderDTO {

    private Car car;

    private User user;

    private LocalDateTime startDate;

}
