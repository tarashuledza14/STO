package org.sto.service;

import org.sto.dto.CarDTO;
import org.sto.dto.CarOrderDTO;
import org.sto.dto.CarPartDTO;
import org.sto.dto.UserDTO;
import org.sto.entity.CarOrder;
import org.sto.entity.enumerable.Status;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface CarOrderService {
    void addCarOrder(final CarOrderDTO carOrderDTO);

    void delete(final Long id);

    List<CarOrder> getAllOrders();
    CarOrder findById(final Long id);
    void updateCarInOrder(final Long orderId,final  CarDTO carDTO);
    void updateUserInOrder(final Long orderId, final UserDTO userDTO);
    List<CarOrder> displayCarsByStatus(final Status status);
    //void updateOrderStatus(final Long carOrderId, final Status status);

    void updateOrderStatus(Long orderId, Status status, Update update);

    //void updateOrderStatus(Long orderId, Status status//, final Update update);

    void addCarPart(final Long orderId, final CarPartDTO carPartDTO);
    void setOrderPrice(final Long orderId,final BigDecimal price);
    List<CarOrder> getCarOrdersBetweenDates(final LocalDateTime startDate, final LocalDateTime endDate);
    BigDecimal getTotalPriceByCarParts(final Long id);
    List<LocalDateTime> getUnavailableDateTime();
    void setStartDate(final Long id,final LocalDateTime startDate);
    void setEndDate(final Long id, final LocalDateTime endDate);
    void deleteCarPart(final Long carOrderId, final  Long carPartId);
}
