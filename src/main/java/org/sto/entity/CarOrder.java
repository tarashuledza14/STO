package org.sto.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.sto.entity.enumerable.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "carOrder")
public class CarOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL)
    private Car car;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;
    @Column
    private LocalDateTime startDate;
    @Column
    private LocalDateTime endDate;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CarPart> carParts;
    @Column
    private Status status;
    @Column
    private BigDecimal price;
}
