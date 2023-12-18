package org.sto.repository;

import org.sto.entity.CarOrder;
import org.sto.entity.User;
import org.sto.entity.enumerable.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarOrderRepository extends JpaRepository<CarOrder,Long> {
    Optional<CarOrder> findAllByStatus(final Status status);
    Optional<CarOrder> findByUser(final User User);
    List<CarOrder> findAllByEndDateBetween(final LocalDateTime startDate, final LocalDateTime endDate);

}
